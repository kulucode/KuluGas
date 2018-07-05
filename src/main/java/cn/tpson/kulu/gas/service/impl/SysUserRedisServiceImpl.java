package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zhangka in 2018/06/13
 */
@Service
public class SysUserRedisServiceImpl implements RedisService<SysUserDTO> {
    @Autowired
    private RedisTemplate<String, SysUserDTO> redisTemplate;

    @Override
    public SysUserDTO get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void put(String key, SysUserDTO value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void put(String key, SysUserDTO value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
