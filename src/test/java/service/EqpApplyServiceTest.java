package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.query.EqpQuery;
import cn.tpson.kulu.gas.repository.EqpDOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * Created by Zhangka in 2018/06/15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqpApplyServiceTest {
    @Autowired
    private EqpApplyService eqpApplyService;
    @Autowired
    private EqpOnlineLogService eqpOnlineLogService;
    @Autowired
    private SysUserBuildingSiteService sysUserBuildingSiteService;
    @Autowired
    private EqpDOMapper eqpDOMapper;
    @Autowired
    private EqpLocationService eqpLocationService;

    @Test
    public void testInsert() {
        /*JSONObject location = new JSONObject();
        location.put("addr", "金华市");
        location.put("lon", 118.294241);
        location.put("lat", 29.70943);*/
        /*EqpApplyDTO eqpApplyDTO = new EqpApplyDTO();
        eqpApplyDTO.setUid(1);
        eqpApplyDTO.setGmtCreate(Instant.now());
        eqpApplyDTO.setLocation(location.toJSONString());

        eqpApplyService.insert(eqpApplyDTO);*/

        /*EqpApplyDTO eqpApplyDTO = eqpApplyService.getById(1);
        eqpApplyDTO.setLocation(location.toJSONString());
        eqpApplyService.updateById(eqpApplyDTO);*/

        /*LocalDateTime now = LocalDateTime.now();
        now = now.withYear(2017);
        System.out.println(eqpOnlineLogService.getWorkingHoursByEqpId(3));
        System.out.println(eqpOnlineLogService.getWorkingHoursByEqpIdAndGmtCreate(3, now.toInstant(ZoneOffset.UTC)));


        SysUserBuildingSiteDTO bs = sysUserBuildingSiteService.getById(2);
        System.out.println(bs.getLocation());
*/
//        System.out.println(eqpDOMapper.getAllId());
//        System.out.println(JSON.toJSONString(eqpDOMapper.selectByEqpNo("2")));
        /*EqpQuery update = new EqpQuery();
        update.setIds(new Integer[0]);
        eqpDOMapper.updateByPrimaryKeys(update);*/
        eqpLocationService.addLocation(3, 36.569, 118.56333);
    }

}
