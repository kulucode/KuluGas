package cn.tpson.kulu.gas.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Collection;

/**
 * Created by Zhangka in 2018/07/05
 */
public interface StatisticsService {
    Collection<JSONObject> statistics();
}
