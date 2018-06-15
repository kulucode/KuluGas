package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.dto.EqpApplyDTO;
import cn.tpson.kulu.gas.service.EqpApplyService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

/**
 * Created by Zhangka in 2018/06/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqpApplyServiceTest {
    @Autowired
    private EqpApplyService eqpApplyService;

    @Test
    public void testInsert() {
        JSONObject location = new JSONObject();
        location.put("addr", "金华市");
        location.put("lon", 118.294241);
        location.put("lat", 29.70943);
        /*EqpApplyDTO eqpApplyDTO = new EqpApplyDTO();
        eqpApplyDTO.setUid(1);
        eqpApplyDTO.setGmtCreate(Instant.now());
        eqpApplyDTO.setLocation(location.toJSONString());

        eqpApplyService.insert(eqpApplyDTO);*/

        EqpApplyDTO eqpApplyDTO = eqpApplyService.getById(1);
        eqpApplyDTO.setLocation(location.toJSONString());
        eqpApplyService.updateById(eqpApplyDTO);
    }
}
