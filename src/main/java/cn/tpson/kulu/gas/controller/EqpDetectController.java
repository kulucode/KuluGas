package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.EqpTypeEnum;
import cn.tpson.kulu.gas.dto.*;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import cn.tpson.kulu.gas.service.EqpDetectService;
import cn.tpson.kulu.gas.service.EqpService;
import cn.tpson.kulu.gas.util.ExcelUtils;
import cn.tpson.kulu.gas.util.RequestContextUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/submit")
    public ResultVO submit(Integer[] ids) {
        int count = 0;
        if (ids != null && ids.length > 0) {
            EqpDetectDTO eqpDetectDTO = new EqpDetectDTO(null, null, null, Instant.now());
            Short userType = getUser().getType();
            Short status = (userType == SysUserDTO.TYPE_BUI) ? EqpDetectDTO.STATUS_SERVICE : userType == SysUserDTO.TYPE_SER ? EqpDetectDTO.STATUS_GOV : null;
            if (status != null) {
                eqpDetectDTO.setStatus(status);
                for (Integer id : ids) {
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
    @PostMapping("/add")
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
    }

    /**
     * 分页查询.
     *
     * @param query
     * @return
     */
    @GetMapping("/page")
    public ResultVO page(EqpDetectQuery query, String st, String et) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(st)) {
            LocalDateTime s = LocalDateTime.parse(st, df);
            query.setStartTime(s);
        }
        if (StringUtils.isNotBlank(et)) {
            LocalDateTime e = LocalDateTime.parse(et, df);
            query.setEndTime(e);
        }
        query.setUid(getUid());
        query.setDeleted(Boolean.FALSE);
        if (query.getType() == null) {
            query.setType(EqpDetectDTO.TYPE_USUAL);
        }

        TableDTO<EqpDetectDTO> tableDTO = eqpDetectService.pageByExample(query);
        List<EqpDetectDTO> rows = tableDTO.getRows();
        rows.forEach(row -> eqpDetectService.initEqp(row));

        Page<EqpDetectDTO> page = new Page<>(query.getOffset(), query.getLimit());
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
    @GetMapping("/search")
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
    }

    /**
     * 导出检测报表.
     *
     * @param type
     * @param st
     * @param et
     */
    @GetMapping("/forms")
    public void forms(Short type, String st, String et) {
        EqpDetectQuery query = new EqpDetectQuery();
        query.setType(type);
        ResultVO resultVO = this.page(query, st, et);
        List<EqpDetectDTO> rows = ((TableDTO<EqpDetectDTO>) resultVO.getData()).getRows();

        // 导出.
        HttpServletResponse resp = RequestContextUtils.getResponse();
        String filename = "检测报表.xlsx";
        String[] titles = {"序号", "设备ID", "设备类型", "机主", "机龄", "铭牌编号", "检测结果", "检测设备编号"};
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
            data.add(row);
        }
        ExcelUtils.httpExport(data, titles, filename, resp);
    }
}
