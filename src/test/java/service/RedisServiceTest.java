package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.dto.SysUserDTO;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zhangka in 2018/06/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    private RedisService<SysUserDTO> redisService;

    @Test
    public void testPutAndGet() {
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUsername("lisi4");
        redisService.put("SYS_USER_456", sysUserDTO, 1, TimeUnit.MINUTES);
        sysUserDTO = redisService.get("SYS_USER_456");

        System.out.println(JSON.toJSONString(sysUserDTO));
    }
}
