package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.EqpStatusEnum;
import cn.tpson.kulu.gas.constant.EqpTypeEnum;
import cn.tpson.kulu.gas.constant.UserTypeEnum;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.query.EqpQuery;
import cn.tpson.kulu.gas.service.*;
import cn.tpson.kulu.gas.util.ExcelUtils;
import cn.tpson.kulu.gas.util.RequestContextUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/14
 */
@RestController
@RequestMapping("/eqp")
public class EqpController extends BaseController {
    private static final Short TYPE_INSTALL = 1;
    private static final Short TYPE_UNINSTALL = 2;

    @Autowired
    private EqpService eqpService;
    @Autowired
    private EqpApplyService eqpApplyService;
    @Autowired
    private EqpDetectService eqpDetectService;
    @Autowired
    private EqpOnlineLogService eqpOnlineLogService;
    @Autowired
    private EqpPunishService eqpPunishService;
    @Autowired
    private EqpLocationCurrentService eqpLocationCurrentService;

    /**
     * 获取设备定位.
     * @return
     */
    @GetMapping("/location")
    public ResultVO location() {
        SysUserDTO user = getUser();
        Integer uid = null;
        if (Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())
                || Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())) {
            uid = user.getId();
        }

        return ResultVO.successResult(eqpLocationCurrentService.getByUid(uid));
    }

    /**
     * 装机申请.
     * @param eqpApplyDTO
     * @return
     */
    @PostMapping(value = "/install")
    public ResultVO install(@RequestBody EqpApplyDTO eqpApplyDTO) {
        return apply(eqpApplyDTO, EqpStatusEnum.STATUS_INSTALL_WAITING.getType());
    }

    /**
     * 拆机申请.
     * @param eqpApplyDTO
     * @return
     */
    @PostMapping(value = "/uninstall")
    public ResultVO uninstall(@RequestBody EqpApplyDTO eqpApplyDTO) {
        return apply(eqpApplyDTO, EqpStatusEnum.STATUS_UNINSTALL_WAITING.getType());
    }

    /**
     * 获取当前用户设备列表.
     * @return
     */
    @GetMapping("/list")
    public ResultVO list(Short status) {
        EqpQuery query = new EqpQuery();
        SysUserDTO user = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER, user.getType()) || Objects.equals(UserTypeEnum.USER_BUI, user.getType())) {
            query.setUid(getUid());
        }
        query.setStatus(status);
        return ResultVO.successResult(eqpService.listByExample(query));
    }

    /**
     * 设备详情.
     * @param eqpId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO detail(Integer eqpId) {
        EqpDTO eqpDTO = eqpService.detail(eqpId);
        return eqpDTO == null ? ResultVO.failResult("设备不存在.") : ResultVO.successResult(eqpDTO);
    }

    /**
     * 批量批准.
     * @param ids
     * @param type
     * @return
     */
    @PostMapping("/status/approve")
    public ResultVO approve(@RequestBody EqpQuery query) {
        Short status;
        if (Objects.equals(TYPE_INSTALL, query.getType())) {
            status = EqpStatusEnum.STATUS_INSTALL_WAITING.getType();
        } else if (Objects.equals(TYPE_UNINSTALL, query.getType())) {
            status = EqpStatusEnum.STATUS_UNINSTALL_WAITING.getType();
        } else {
            return ResultVO.failResult("类型错误.");
        }

        int count = status(query.getIds(), status);
        return  ResultVO.successResult(count);
    }

    /**
     * eqpDTO
     * @param ids
     * @param type
     * @return
     */
    @PostMapping("/status/refuse")
    public ResultVO refuse(@RequestBody EqpQuery query) {
        Short status;
        if (Objects.equals(TYPE_INSTALL, query.getType())) {
            status = EqpStatusEnum.STATUS_INSTALL_REFUSE.getType();
        } else if (Objects.equals(TYPE_UNINSTALL, query.getType())) {
            status = EqpStatusEnum.STATUS_UNINSTALL_REFUSE.getType();
        } else {
            return ResultVO.failResult("类型错误.");
        }
        int count = status(query.getIds(), status);
        return  ResultVO.successResult(count);
    }

    /**
     *  重审.
     * @param ids
     * @param type
     * @return
     */
    @PostMapping("/status/back")
    public ResultVO back(@RequestBody EqpQuery query) {
        Short status;
        if (Objects.equals(TYPE_INSTALL, query.getType())) {
            status = EqpStatusEnum.STATUS_INSTALL_WAITING.getType();
        } else if (Objects.equals(TYPE_UNINSTALL, query.getType())) {
            status = EqpStatusEnum.STATUS_UNINSTALL_WAITING.getType();
        } else {
            return ResultVO.failResult("类型错误.");
        }

        int count = status(query.getIds(), status);
        return  ResultVO.successResult(count);
    }

    /**
     * 取消申请.
     * @param ids
     * @return
     */
    @PostMapping("/status/cancel")
    public ResultVO cancel(@RequestBody IdsDTO ids) {
        EqpQuery eqpQuery = new EqpQuery();
        eqpQuery.setStatus(EqpStatusEnum.STATUS_UNINSTALL_CANCEL.getType());
        eqpQuery.setIds(ids.getIds());
        eqpQuery.setGmtModified(Instant.now());

        int count = eqpService.updateByIds(eqpQuery);
        return  ResultVO.successResult(count);
    }

    /**
     * 设备台账.
     * @param query
     * @return
     */
    @GetMapping("/page")
    public ResultVO page(EqpQuery query) {
        query.setType(EqpTypeEnum.typeOf(query.getKey()));
        SysUserDTO user = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())
                || Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
            query.setUid(getUid());
        }
        query.setDeleted(Boolean.FALSE);

        TableDTO<EqpDTO> tableDTO = eqpService.pageByExample(query);
        Page<EqpDTO> page = new Page<>(query.getOffset(), query.getLimit());
        List<EqpDTO> rows = tableDTO.getRows();
        rows.forEach(row -> {
            row.setDetectValue(eqpDetectService.getDetectValueByEqpId(row.getId()));
            row.setWorkingHours(eqpOnlineLogService.getWorkingHoursByEqpId(row.getId()));
            row.setPunishCount(eqpPunishService.getPunishCountByEqpNo(row.getEqpNo(), (short)LocalDate.now().getYear()));
        });
        page.setTotalRow(tableDTO.getTotal());
        page.setList(tableDTO.getRows());
        return ResultVO.successResult(page);
    }

    /**
     * 导出设备台账报表.
     *
     * @param st
     * @param et
     */
    @GetMapping("/forms")
    public void forms(EqpQuery query) {
        SysUserDTO user = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), user.getType())
                || Objects.equals(UserTypeEnum.USER_BUI.getType(), user.getType())) {
            query.setUid(getUid());
        }
        query.setDeleted(Boolean.FALSE);
        TableDTO<EqpDTO> tableDTO = eqpService.pageByExample(query);
        List<EqpDTO> rows = tableDTO.getRows();

        // 导出.
        HttpServletResponse resp = RequestContextUtils.getResponse();
        List<String[]> data = new ArrayList<>(50);
        for (int i = 0; i < rows.size(); ++i) {
            EqpDTO eqp = rows.get(i);
            eqp.setDetectValue(eqpDetectService.getDetectValueByEqpId(eqp.getId()));
            eqp.setWorkingHours(eqpOnlineLogService.getWorkingHoursByEqpId(eqp.getId()));
            eqp.setPunishCount(eqpPunishService.getPunishCountByEqpNo(eqp.getEqpNo(), (short)LocalDate.now().getYear()));

            String[] row = {
                    String.valueOf(i + 1),
                    eqp.getEqpNo(),
                    EqpTypeEnum.nameOf(eqp.getType()),
                    eqp.getBrand(),
                    String.valueOf(eqp.getAge()),
                    eqp.getDataPlate(),
                    eqp.getDetectValue() == null ? "" : String.valueOf(eqp.getDetectValue()),
                    eqp.getWorkingHours() == null ? "" : String.valueOf(eqp.getWorkingHours()),
                    eqp.getPunishCount() == null ? "" : String.valueOf(eqp.getPunishCount()),
                    eqp.getOwnerName()
            };
            data.add(row);
        }

        String filename = "设备台账报表.xlsx";
        String[] titles = {"序号", "设备ID", "设备类型", "设备品牌", "机龄", "铭牌编号", "最后一次检测结果", "累积工作时间", "年度被处罚次数", "机主"};
        ExcelUtils.httpExport(data, titles, filename, resp);

    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////
    protected int status(Integer[] ids, Short status) {
        EqpQuery eqpQuery = new EqpQuery();
        eqpQuery.setStatus(status);
        eqpQuery.setIds(ids);
        eqpQuery.setGmtModified(Instant.now());

        return eqpService.updateByIds(eqpQuery);
    }

    /**
     * 装机/拆机申请.
     * @param eqpApplyDTO
     * @return
     */
    protected ResultVO apply(EqpApplyDTO eqpApplyDTO, Short status) {
        if (eqpApplyDTO.getEqps() == null || eqpApplyDTO.getEqps().size() == 0) {
            return ResultVO.failResult("请添加设备.");
        }

        // 设置机主姓名
        SysUserDTO sysUserDTO = getUser();
        if (Objects.equals(UserTypeEnum.USER_PER.getType(), sysUserDTO.getType())) {
            eqpApplyDTO.getEqps().forEach(eqpDTO -> eqpDTO.setOwnerName(sysUserDTO.getRealname()));
        }

        Instant now = Instant.now();
        eqpApplyDTO.setUid(sysUserDTO.getId());
        eqpApplyDTO.setLocation(JSON.toJSONString(eqpApplyDTO.getGps()));
        eqpApplyDTO.setGmtCreate(now);
        eqpApplyDTO.setDeleted(Boolean.FALSE);
        eqpApplyService.apply(eqpApplyDTO, status);

        return ResultVO.successResult();
    }
}
