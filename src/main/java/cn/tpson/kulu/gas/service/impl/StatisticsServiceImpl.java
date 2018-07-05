package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.dto.StatisticsDTO;
import cn.tpson.kulu.gas.repository.StatisticsMapper;
import cn.tpson.kulu.gas.service.StatisticsService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Zhangka in 2018/07/05
 */
@Service
@Transactional(readOnly = true)
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public Collection<JSONObject> statistics() {
        List<StatisticsDTO> online = statisticsMapper.countOnlineOfYear();
        List<StatisticsDTO> detect = statisticsMapper.countDetectOfYear();
        List<StatisticsDTO> punish = statisticsMapper.countPunishOfYear();

        Map<String, JSONObject> map = new TreeMap<>();
        for (StatisticsDTO statisticsDTO : online) {
            JSONObject element = new JSONObject();
            element.put("online", statisticsDTO.getTotal());
            element.put("date", statisticsDTO.getDate());
            map.put(statisticsDTO.getDate(), element);
        }

        for (StatisticsDTO statisticsDTO : detect) {
            JSONObject element = map.get(statisticsDTO.getDate());
            if (element == null) {
                element = new JSONObject();
                element.put("date", statisticsDTO.getDate());
                map.put(statisticsDTO.getDate(), element);
            }
            element.put("detected", statisticsDTO.getTotal());
        }

        for (StatisticsDTO e : punish) {
            JSONObject element = map.get(e.getDate());
            if (element == null) {
                element = new JSONObject();
                element.put("date", e.getDate());
                map.put(e.getDate(), element);
            }
            element.put("punished", e.getTotal());
        }

        return map.values();
    }
}
