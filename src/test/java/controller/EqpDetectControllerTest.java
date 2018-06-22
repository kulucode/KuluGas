package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.query.EqpDetectQuery;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Zhangka in 2018/06/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqpDetectControllerTest {
    @Autowired
    private EqpDetectController eqpDetectController;

    @Test
    public void testPage() {
        EqpDetectQuery query = new EqpDetectQuery();
        query.setOffset(0);
        query.setLimit(10);
        System.out.println(JSON.toJSONString(eqpDetectController.page(query)));
    }
}
