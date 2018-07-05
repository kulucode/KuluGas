package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.EqpLocationDO;
import cn.tpson.kulu.gas.dto.EqpDTO;
import cn.tpson.kulu.gas.dto.EqpLocationCurrentDTO;
import cn.tpson.kulu.gas.dto.EqpLocationDTO;
import cn.tpson.kulu.gas.dto.LocationDTO;
import cn.tpson.kulu.gas.query.EqpLocationQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.EqpLocationDOMapper;
import cn.tpson.kulu.gas.service.EqpLocationCurrentService;
import cn.tpson.kulu.gas.service.EqpLocationService;
import cn.tpson.kulu.gas.util.DituUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Zhangka in 2018/07/02
 */
@Service
@Transactional(readOnly = true)
public class EqpLocationServiceImpl extends BaseServiceImpl<EqpLocationDTO, EqpLocationDO, EqpLocationQuery> implements EqpLocationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EqpLocationServiceImpl.class);

    @Autowired
    private EqpLocationDOMapper eqpLocationDOMapper;
    @Autowired
    private EqpLocationCurrentService eqpLocationCurrentService;
    @Autowired
    private EqpLocationService eqpLocationService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addLocation(Integer eqpId, Double lat, Double lon) {
        Instant now = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        String queryDay = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Integer id = eqpLocationService.getId(eqpId, queryDay);
        LocationDTO loc = new LocationDTO(lat, lon, localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if (id != null) {
            EqpLocationDTO eqpLocationDTO = new EqpLocationDTO(id, null, null, now);
            eqpLocationDTO.setLocation(JSON.toJSONString(loc));
            eqpLocationDOMapper.addLocation(eqpLocationDTO);
        } else {
            EqpLocationDTO eqpLocationDTO = new EqpLocationDTO(null, Boolean.FALSE, now, now);
            LocationDTO[] locationDTOS = {loc};
            eqpLocationDTO.setLocation(JSONArray.toJSONString(locationDTOS));
            eqpLocationDTO.setEqpId(eqpId);
            insert(eqpLocationDTO);
        }

        try {
            DituUtils.Area area = DituUtils.getArea(lat, lon);
            EqpLocationCurrentDTO d = eqpLocationCurrentService.getByEqpId(eqpId);
            if (d == null) {
                d = new EqpLocationCurrentDTO(null, Boolean.FALSE, now, now);
                d.setAddr(area.getAddr());
                d.setArea(area.getArea());
                d.setEqpId(eqpId);
                d.setLat(lat);
                d.setLon(lon);
                eqpLocationCurrentService.insert(d);
            } else {
                d.setAddr(area.getAddr());
                d.setArea(area.getArea());
                d.setLat(lat);
                d.setLon(lon);
                d.setGmtModified(now);
                eqpLocationCurrentService.updateById(d);
            }
        } catch (Throwable e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("通过经纬度获取详细地址出错.lat:{}, lon:{}, msg:{}", lat, lon, e.getMessage());
            }
        }

        return 1;
    }

    @Override
   public Integer getId(Integer eqpId, String queryDay) {
        EqpLocationQuery query = new EqpLocationQuery();
        query.setEqpId(eqpId);
        query.setQueryDay(queryDay);
        return eqpLocationDOMapper.getId(query);
    }

    @Override
    public BaseMapper<EqpLocationQuery, EqpLocationDO> mapper() {
        return eqpLocationDOMapper;
    }
}
