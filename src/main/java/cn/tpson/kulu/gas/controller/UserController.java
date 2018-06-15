package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.cache.SysUserCache;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.dto.SysUserPersonalDTO;
import cn.tpson.kulu.gas.service.SysUserService;
import cn.tpson.kulu.gas.util.CookieUtils;
import cn.tpson.kulu.gas.util.JsonUtils;
import cn.tpson.kulu.gas.util.UUIDUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

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
    public ResultVO registerPersonal(@Valid SysUserDTO sysUserDTO, @Valid SysUserPersonalDTO sysUserPersonalDTO) {
        Instant now = Instant.now();
        sysUserDTO.setGmtCreate(now);
        sysUserDTO.setStatus(SysUserDTO.STATUS_WAITING);
        sysUserDTO.setType(SysUserDTO.TYPE_PER);

        sysUserPersonalDTO.setGmtCreate(now);
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
        sysUserDTO.setType(SysUserDTO.TYPE_BUI);

        sysUserBuildingSiteDTO.setGmtCreate(now);
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
                          @NotBlank(message = "密码不能为空.") String password,
                          HttpServletRequest request, HttpServletResponse response) {

        SysUserDTO sysUserDTO = sysUserService.getByUsername(username);
        if (sysUserDTO == null)
            return ResultVO.failResult("用户不存在.");
        if (!password.equalsIgnoreCase(sysUserDTO.getPassword()))
            return ResultVO.failResult("密码错误.");
        if (SysUserDTO.STATUS_WAITING == sysUserDTO.getStatus())
            return ResultVO.failResult("账户状态:待审核.");

        String sid = CookieUtils.getCookie(request, "sid");
        if (StringUtils.isNotBlank(sid)) {
            sysUserCache.delete(sid);
        }
        sid = UUIDUtils.uuid();
        CookieUtils.setCookie(response, SysUserDTO.TOKEN_NAME, sid, SysUserCache.TIMEOUT * 60);
        sysUserCache.put(sid, sysUserDTO);

        JSONObject result = JsonUtils.builder().put(SysUserDTO.TOKEN_NAME, sid).put("type", sysUserDTO.getType()).build();
        return ResultVO.successResult(result);
    }

    /**
     * 获取用户详情.
     * @return
     */
    @GetMapping("/detail")
    public ResultVO detail() {
        SysUserDTO sysUserDTO = getUser();
        if (sysUserDTO.getType() == SysUserDTO.TYPE_BUI) {
            sysUserService.initPersonal(sysUserDTO);
        } else if (sysUserDTO.getType() == SysUserDTO.TYPE_PER) {
            sysUserService.initBuildingSite(sysUserDTO);
        }

        return ResultVO.successResult(sysUserDTO);
    }
}
