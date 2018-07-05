package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.dto.StatisticsDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhangka in 2018/07/05
 */
@Repository
public interface StatisticsMapper {
    /**
     * 本年每月上线总数(上线设备数量).
     * @return
     */
    List<StatisticsDTO> countOnlineOfYear();

    /**
     * 本年每月检测总数(检测设备数量).
     * @return
     */
    List<StatisticsDTO> countDetectOfYear();

    /**
     * 本年每月处罚总数(处罚设备数量).
     * @return
     */
    List<StatisticsDTO> countPunishOfYear();
}
