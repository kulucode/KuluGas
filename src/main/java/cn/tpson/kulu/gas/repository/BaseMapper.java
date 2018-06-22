package cn.tpson.kulu.gas.repository;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/04
 */
public interface BaseMapper<QUERY, DO> {
    int insert(DO record);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKey(DO record);

    int updateByPrimaryKeys(QUERY update);

    DO selectByPrimaryKey(Integer id);

    List<DO> selectAll();

    List<DO> selectByExample(QUERY example);

    int count();

    int countByExample(QUERY example);
}
