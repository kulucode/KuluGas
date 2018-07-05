package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.EqpTypeEnum;
import cn.tpson.kulu.gas.constant.UserTypeEnum;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.query.EqpDetectLogQuery;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import cn.tpson.kulu.gas.service.*;
import cn.tpson.kulu.gas.util.ExcelUtils;
import cn.tpson.kulu.gas.util.RequestContextUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/19
 */
@RestController
@RequestMapping("/eqp/detect")
@Validated
public class EqpDetectController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EqpDetectController.class);
    @Autowired
    private EqpService eqpService;
    @Autowired
    private EqpDetectService eqpDetectService;
    @Autowired
    private EqpDetectLogService eqpDetectLogService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private EqpOnlineLogService eqpOnlineLogService;

    /**
     * 提交审核.
     * @param ids
     * @return
     */
    @PostMapping("/submit")
    public ResultVO submit(@RequestBody IdsDTO ids) {
        int count = 0;
        if (ids.getIds() != null && ids.getIds().length > 0) {
            EqpDetectDTO eqpDetectDTO = new EqpDetectDTO();
            eqpDetectDTO.setGmtModified(Instant.now());
            Short userType = getUser().getType();
            Short status = null;
            if (Objects.equals(UserTypeEnum.USER_BUI.getType(), userType)) {
                status = EqpDetectDTO.STATUS_SERVICE;
            } else if (Objects.equals(UserTypeEnum.USER_SER.getType(), userType)) {
                status = EqpDetectDTO.STATUS_GOV;
            }

            if (status != null) {
                eqpDetectDTO.setStatus(status);
                for (Integer id : ids.getIds()) {
                    eqpDetectDTO.setId(id);
                    count += eqpDetectService.updateById(eqpDetectDTO);
                }
            }
        }

        return ResultVO.successResult(count);
    }

    /**
     * 添加检测记录.
     *
     * @param eqpId
     * @param detectValue
     * @param detectNo
     * @return
     */
    /*@PostMapping("/add")
    public ResultVO add(@NotNull(message = "设备ID不能为空.") Integer eqpId,
                        @NotBlank(message = "检测值不能为空.") String detectValue,
                        @NotBlank(message = "检测设备号不能为空.") String detectNo, Short type) {
        if (!NumberUtils.isParsable(detectValue)) {
            return ResultVO.failResult("检测值格式错误.");
        }

        EqpDTO eqpDTO = eqpService.getById(eqpId);
        if (eqpDTO == null) {
            return ResultVO.failResult("被检测设备不存在.");
        }

        int id = eqpDetectService.add(eqpId, detectValue, detectNo, type);
        return ResultVO.successResult(id);
    }*/

    /**
     * 分页查询设备检测记录.
     *
     * @return
     */
    @GetMapping("/page")
    public ResultVO page(EqpDetectQuery query) {
        query.setEqpTypeKey(EqpTypeEnum.typeOf(query.getKey()));
        SysUserDTO user = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())
                || Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
            query.setUid(getUid());
        }
        query.setDeleted(Boolean.FALSE);

        TableDTO<EqpDetectDTO> tableDTO = eqpDetectService.pageByExample(query);
        List<EqpDetectDTO> rows = tableDTO.getRows();
        rows.forEach(row -> {
            eqpDetectService.initEqp(row);
            eqpDetectService.initUser(row);
            eqpService.initUser(row.getEqp());
            sysUserService.initPersonal(row.getEqp().getUser());
            sysUserService.initBuildingSite(row.getEqp().getUser());
        });

        Page<EqpDetectDTO> page = new Page<>(query.getOffset(), query.getLimit());
        page.setTotalRow(tableDTO.getTotal());
        page.setList(rows);
        return ResultVO.successResult(page);
    }

    /**
     * 设备历史检测记录.
     * @param query
     * @return
     */
    @GetMapping("/log/page")
    public ResultVO page(EqpDetectLogQuery query) {
        SysUserDTO user = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())
                || Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
            query.setUid(getUid());
        }
        query.setDeleted(Boolean.FALSE);
        TableDTO<EqpDetectLogDTO> tableDTO = eqpDetectLogService.pageByExample(query);
        List<EqpDetectLogDTO> rows = tableDTO.getRows();
        rows.forEach(row -> {
            eqpDetectLogService.initEqp(row);
            eqpDetectLogService.initUser(row);
            eqpService.initUser(row.getEqp());
            sysUserService.initPersonal(row.getEqp().getUser());
            sysUserService.initBuildingSite(row.getEqp().getUser());
        });

        Page<EqpDetectLogDTO> page = new Page<>(query.getOffset(), query.getLimit());
        page.setTotalRow(tableDTO.getTotal());
        page.setList(rows);
        return ResultVO.successResult(page);
    }
    /**
     * 检测记录搜索.
     *
     * @param key
     * @return
     */
    /*@GetMapping("/search")
    public ResultVO search(String key, Integer offset, Integer limit) {
        if (StringUtils.isBlank(key)) {
            return ResultVO.failResult("关键字不能为空.");
        }
        EqpDetectQuery query = new EqpDetectQuery();
        query.setKey(key);
        query.setEqpType(EqpTypeEnum.typeOf(key));
        query.setOffset(offset);
        query.setLimit(limit);

        TableDTO<EqpDetectDTO> tableDTO = eqpDetectService.search(query);
        List<EqpDetectDTO> rows = tableDTO.getRows();
        rows.forEach(row -> eqpDetectService.initEqp(row));

        Page<EqpDetectDTO> page = new Page<>(query.getOffset(), query.getLimit());
        page.setTotalRow(tableDTO.getTotal());
        page.setList(rows);
        return ResultVO.successResult(tableDTO);
    }*/

    /**
     * 导出检测报表.
     *
     * @param type
     * @param st
     * @param et
     */
    @GetMapping("/forms")
    public void forms(EqpDetectQuery query) {
        SysUserDTO user = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())
                || Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
            query.setUid(getUid());
        }
        query.setType(query.getType() == null ? EqpDetectDTO.TYPE_USUAL : query.getType());
        query.setDeleted(Boolean.FALSE);
        TableDTO<EqpDetectDTO> tableDTO = eqpDetectService.pageByExample(query);
        List<EqpDetectDTO> rows = tableDTO.getRows();

        // 导出.
        HttpServletResponse resp = RequestContextUtils.getResponse();
        List<String[]> data = new ArrayList<>(50);
        for (int i = 0; i < rows.size(); ++i) {
            EqpDetectDTO dto = rows.get(i);
            String[] row = {
                    String.valueOf(i + 1),
                    dto.getEqp().getEqpNo(),
                    EqpTypeEnum.nameOf(dto.getEqp().getType()),
                    dto.getEqp().getOwnerName(),
                    String.valueOf(dto.getEqp().getAge()),
                    dto.getEqp().getDataPlate(),
                    dto.getDetectValue().toString(),
                    dto.getDetectNo()
            };

            if (Objects.equals(EqpDetectDTO.TYPE_FIRST, query.getType())) {
                Integer hours = eqpOnlineLogService.getWorkingHoursByEqpIdAndGmtCreate(dto.getEqpId(), dto.getGmtDetect());
                row[5] = hours == null ? "" : String.valueOf(hours);
            }
            data.add(row);
        }

        if (Objects.equals(EqpDetectDTO.TYPE_FIRST, query.getType())) {
            String filename = "进场检测报表.xlsx";
            String[] titles = {"序号", "设备ID", "设备类型", "机主", "机龄", "铭牌编号", "检测结果", "检测设备编号"};
            ExcelUtils.httpExport(data, titles, filename, resp);
        } else {
            String filename = "日常检测报表.xlsx";
            String[] titles = {"序号", "设备ID", "设备类型", "机主", "机龄", "距离上次检测工时", "检测结果", "检测设备编号"};
            ExcelUtils.httpExport(data, titles, filename, resp);
        }
    }
}
