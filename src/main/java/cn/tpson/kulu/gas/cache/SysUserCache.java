package cn.tpson.kulu.gas.cache;

import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SysUserCache implements Cache<String, SysUserDTO> {
    public static final int TIMEOUT = 7 * 24;

    @Autowired
    private RedisService<SysUserDTO> redisService;

    @Override
    public SysUserDTO get(String key) {
        return StringUtils.isBlank(key) ? null : redisService.get(key);
    }

    @Override
    public void put(String key, SysUserDTO value) {
        redisService.put(key, value, TIMEOUT, TimeUnit.HOURS);
    }

    @Override
    public Boolean delete(String key) {
        return redisService.delete(key);
    }
}
