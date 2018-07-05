package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.cache.SysUserCache;
import cn.tpson.kulu.gas.constant.ErrorCodeEnum;
import cn.tpson.kulu.gas.constant.UserTypeEnum;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.exception.ParamRuntimeException;
import cn.tpson.kulu.gas.query.SysUserQuery;
import cn.tpson.kulu.gas.service.SysUserService;
import cn.tpson.kulu.gas.util.*;
import cn.tpson.kulu.gas.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/08
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserCache sysUserCache;

    /**
     * 个人机主注册.
     * @param sysUserDTO
     * @param sysUserPersonalDTO
     * @return
     */
    @PostMapping("/register/personal")
    public ResultVO registerPersonal(@Valid SysUserDTO sysUserDTO,
                                     @Valid SysUserPersonalDTO sysUserPersonalDTO) {
        if (StringUtils.isBlank(sysUserDTO.getRealname())) {
            throw new ParamRuntimeException("姓名不能为空.");
        }

        Instant now = Instant.now();
        sysUserDTO.setGmtCreate(now);
        sysUserDTO.setStatus(SysUserDTO.STATUS_WAITING);
        sysUserDTO.setType(UserTypeEnum.USER_PER.getType());
        sysUserDTO.setDeleted(Boolean.FALSE);

        sysUserPersonalDTO.setGmtCreate(now);
        sysUserPersonalDTO.setDeleted(Boolean.FALSE);
        sysUserDTO.setPersonal(sysUserPersonalDTO);

        int id = sysUserService.insert(sysUserDTO);
        return ResultVO.successResult(id);
    }

    /**
     * 施工单位注册.
     * @param sysUserDTO
     * @param sysUserBuildingSiteDTO
     * @return
     */
    @PostMapping("/register/site")
    public ResultVO registerBuildingSite(@Valid SysUserDTO sysUserDTO,
                                         @Valid SysUserBuildingSiteDTO sysUserBuildingSiteDTO) {
        Instant now = Instant.now();
        sysUserDTO.setGmtCreate(now);
        sysUserDTO.setStatus(SysUserDTO.STATUS_WAITING);
        sysUserDTO.setType(UserTypeEnum.USER_BUI.getType());
        sysUserDTO.setDeleted(Boolean.FALSE);

        sysUserBuildingSiteDTO.setGmtCreate(now);
        sysUserBuildingSiteDTO.setDeleted(Boolean.FALSE);
        sysUserBuildingSiteDTO.setLocation(JSON.toJSONString(sysUserBuildingSiteDTO.getGps()));
        sysUserDTO.setBuildingSite(sysUserBuildingSiteDTO);

        int id = sysUserService.insert(sysUserDTO);
        return ResultVO.successResult(id);
    }

    /**
     * 用户登录.
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
    public ResultVO login(@NotBlank(message = "用户名不能为空.") String username,
                          @NotBlank(message = "密码不能为空.") String password) {
        SysUserDTO sysUserDTO = sysUserService.getByUsername(username);
        if (sysUserDTO == null)
            return ResultVO.failResult("用户不存在.");
        if (!password.equalsIgnoreCase(sysUserDTO.getPassword()))
            return ResultVO.failResult("密码错误.");
        if (Objects.equals(SysUserDTO.STATUS_WAITING, sysUserDTO.getStatus()))
            return ResultVO.failResult("账户正在审核中.");
        if (Objects.equals(SysUserDTO.STATUS_REFUSE, sysUserDTO.getStatus()))
            return ResultVO.failResult("账户审核被拒绝.");

        String sid = getSid();
        if (StringUtils.isNotBlank(sid)) {
            sysUserCache.delete(sid);
        }
        sid = UUIDUtils.uuid();
        CookieUtils.setCookie(RequestContextUtils.getResponse(), SysUserDTO.SID, sid, SysUserCache.TIMEOUT * 60);
        sysUserCache.put(sid, sysUserDTO);

        JSONObject result = JsonUtils.builder().put(SysUserDTO.SID, sid).put("type", sysUserDTO.getType()).build();
        return ResultVO.successResult(result);
    }

    /**
     * 登出.
     * @throws IOException
     */
    @GetMapping("/logout")
    public ResultVO logout() {
        String sid = RequestContextUtils.getValue(SysUserDTO.SID);
        if (StringUtils.isNotBlank(sid)) {
            sysUserCache.delete(sid);
            CookieUtils.delCookie(RequestContextUtils.getResponse(), SysUserDTO.SID);
        }

        return ResultVO.successResult();
    }

    /**
     * 获取用户详情.
     * @return
     */
    @GetMapping("/detail")
    public ResultVO detail(Integer uid) {
        SysUserDTO user = sysUserService.getById(uid);
        CheckUtils.checkNull(user, ErrorCodeEnum.USER_NOT_FOUND);

        Short type = user.getType();
        if (Objects.equals(UserTypeEnum.USER_BUI.getType(), type)) {
            sysUserService.initBuildingSite(user);
        } else if (Objects.equals(UserTypeEnum.USER_PER.getType(), type)) {
            sysUserService.initPersonal(user);
        }

        return ResultVO.successResult(user);
    }

    /**
     * 分页查询.
     * @param query
     * @param st
     * @param et
     * @return
     */
    @GetMapping("/page")
    public ResultVO page(SysUserQuery query) {
        query.setType(UserTypeEnum.typeOf(query.getKey()));
        query.setDeleted(Boolean.FALSE);
        TableDTO<SysUserDTO> tableDTO = sysUserService.pageByExample(query);
        List<SysUserDTO> rows = tableDTO.getRows();
        rows.forEach(row -> {
            sysUserService.initPersonal(row);
            sysUserService.initBuildingSite(row);
        });

        Page<SysUserDTO> page = new Page<>(query.getOffset(), query.getLimit());
        page.setTotalRow(tableDTO.getTotal());
        page.setList(rows);
        return ResultVO.successResult(page);
    }

    /**
     * 批量批准.
     * @param ids
     * @return
     */
    @PostMapping("/status/approve")
    public ResultVO approve(@RequestBody IdsDTO ids) {
        int count = status(ids.getIds(), SysUserDTO.STATUS_NORMAL);
        return  ResultVO.successResult(count);
    }

    /**
     *  批量拒绝.
     * @param ids
     * @return
     */
    @PostMapping("/status/refuse")
    public ResultVO refuse(@RequestBody IdsDTO ids) {
        int count = status(ids.getIds(), SysUserDTO.STATUS_REFUSE);
        return  ResultVO.successResult(count);
    }

    /**
     *  重审.
     * @param ids
     * @return
     */
    @PostMapping("/status/back")
    public ResultVO back(@RequestBody IdsDTO ids) {
        int count = status(ids.getIds(), SysUserDTO.STATUS_WAITING);
        return  ResultVO.successResult(count);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    protected int status(Integer[] ids, Short status) {
        SysUserQuery sysUserQuery = new SysUserQuery();
        sysUserQuery.setStatus(status);
        sysUserQuery.setIds(ids);
        sysUserQuery.setGmtModified(Instant.now());

        return sysUserService.updateByIds(sysUserQuery);
    }
}
