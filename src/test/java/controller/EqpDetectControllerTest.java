package controller;

import cn.tpson.kulu.gas.controller.DeviceController;
import cn.tpson.kulu.gas.controller.EqpDetectController;
import cn.tpson.kulu.gas.query.EqpDetectQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Zhangka in 2018/06/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EqpDetectControllerTest {
    @Autowired
    private EqpDetectController eqpDetectController;
    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testPage() {
        EqpDetectQuery query = new EqpDetectQuery();
        query.setOffset(0);
        query.setLimit(10);
//        System.out.println(JSON.toJSONString(eqpDetectController.page(query, null, null)));
    }

    @Autowired
    private DeviceController deviceController;

    @Test
    public void testOnline() {
//        deviceController.online("7", null, "12.36", "118.59");
//        deviceController.offline("7", "12.36", "118.89");
//        deviceController.location("7", "38.97646", "112.3039");
//        deviceController.detect("7", "67676", "0.56");
//        System.out.println(deviceController.s());
        /*String url1 = "http://gc.ditu.aliyun.com/regeocoding?l=${lat},${lon}&type=010";
        String json = restTemplate.getForEntity(url1, String.class, 39.97646, 116.3039).getBody();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("addrList");
        JSONObject j_2 = jsonArray.getJSONObject(0);
        String allAdd = j_2.getString("admName");
        System.out.println(allAdd);
        System.out.println(j_2.getString("name"));*/
        System.out.println("=============================");
    }
}
