package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.dto.Page;
import cn.tpson.kulu.gas.repository.BaseMapper;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface BaseService<DTO, DO, QUERY> {
    BaseMapper<QUERY, DO> mapper();

    int insert(DTO record);

    int deleteById(Long id);

    int updateById(DTO record);

    DTO getById(Long id);

    List<DTO> list();

    List<DTO> listByExample(QUERY example);

    int count();

    int countByExample(QUERY example);

    Page<DTO> pageByExample(QUERY example);
}
