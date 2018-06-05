package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.dto.BaseDTO;
import cn.tpson.kulu.gas.dto.Page;
import cn.tpson.kulu.gas.repository.BaseMapper;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface BaseService<DTO, DO> {
    BaseMapper<DTO, DO> mapper();

    long insert(DTO record);

    int deleteById(Long id);

    int updateById(DTO record);

    DTO getById(Long id);

    List<DTO> list();

    List<DTO> listByExample(DTO example);

    long count();

    long countByExample(DTO example);

    Page<DTO> pageByExample(Page<DTO> page);
}
