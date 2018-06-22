package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.service.BaseService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public abstract class BaseServiceImpl<DTO, DO, QUERY> implements BaseService<DTO, DO, QUERY> {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(DTO record) {
        Class<DO> clazz = getGenericClassForDo();
        DO d = BeanUtils.copyProperties(clazz, record);
        mapper().insert(d);
        Method method;
        int id;
        try {
            method = clazz.getMethod("getId");
            id = (Integer) method.invoke(d);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Integer id) {
        return mapper().deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateById(DTO record) {
        return mapper().updateByPrimaryKey(BeanUtils.copyProperties(getGenericClassForDo(), record));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByIds(QUERY query) {
        return mapper().updateByPrimaryKeys(query);
    }

    @Override
    public DTO getById(Integer id) {
        DO record = mapper().selectByPrimaryKey(id);
        return BeanUtils.copyProperties(getGenericClassForDto(), record);
    }

    @Override
    public List<DTO> list() {
        List<DO> list =  mapper().selectAll();
        return BeanUtils.copyPropertiesForList(getGenericClassForDto(), list);
    }

    @Override
    public List<DTO> listByExample(QUERY example) {
        List<DO> list = mapper().selectByExample(example);
        return BeanUtils.copyPropertiesForList(getGenericClassForDto(), list);
    }

    @Override
    public int count() {
        return mapper().count();
    }

    @Override
    public int countByExample(QUERY example) {
        return mapper().countByExample(example);
    }

    @Override
    public TableDTO<DTO> pageByExample(QUERY example) {
        int total = countByExample(example);
        List<DTO> rows = listByExample(example);
        return new TableDTO<>(total, rows);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    protected Class<DTO> getGenericClassForDto() {
        return getGenericClass(0);
    }

    protected Class<DO> getGenericClassForDo() {
        return getGenericClass(1);
    }

    @SuppressWarnings("unchecked")
    protected <T> Class<T> getGenericClass(int idx) {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[idx];
        return (Class<T>)type;
    }
}
