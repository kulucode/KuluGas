package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.EqpPunishDO;
import cn.tpson.kulu.gas.dto.EqpPunishDTO;
import cn.tpson.kulu.gas.query.EqpPunishQuery;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Zhangka in 2018/07/02
 */
public interface EqpPunishService extends BaseService<EqpPunishDTO, EqpPunishDO, EqpPunishQuery> {
    String getPunishNumber();

    EqpPunishDTO punish(Integer eqpId, String amount, String detectNo, Date repairDate, Double lat, Double lon, Double detectValue, Short type, String bsName);

    Integer getPunishCountByEqpNo(String eqpNo, Short year);
}
