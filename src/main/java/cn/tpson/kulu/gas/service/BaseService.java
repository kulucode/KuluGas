package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.repository.BaseMapper;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface BaseService<DTO, DO, QUERY> {
    BaseMapper<QUERY, DO> mapper();

    int insert(DTO record);

    int deleteById(Integer id);

    int updateById(DTO record);

    int updateByIds(QUERY query);

    DTO getById(Integer id);

    List<DTO> list();

    List<DTO> listByExample(QUERY example);

    int count();

    int countByExample(QUERY example);

    TableDTO<DTO> pageByExample(QUERY example);
}
