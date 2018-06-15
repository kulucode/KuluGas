package cn.tpson.kulu.gas.cache;

/**
 * Created by Zhangka in 2018/06/13
 */
public interface Cache<K, V> {
    V get(K key);

    void put(K key, V value);

    Boolean delete(K key);
}
