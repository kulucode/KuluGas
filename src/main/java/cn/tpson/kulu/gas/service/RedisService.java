package cn.tpson.kulu.gas.service;

import java.util.concurrent.TimeUnit;

/**
 * Created by Zhangka in 2018/06/13
 */
public interface RedisService<V> {
    V get(String key);

    void put(String key, V value);

    void put(String key, V value, long timeout, TimeUnit unit);

    Boolean delete(String key);
}
