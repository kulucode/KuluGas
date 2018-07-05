package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.cache.SysUserCache;
import cn.tpson.kulu.gas.domain.SysBlockChainContentDO;
import cn.tpson.kulu.gas.domain.SysBlockChainDO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.dto.TableDTO;
import cn.tpson.kulu.gas.repository.SysBlockChainContentDOMapper;
import cn.tpson.kulu.gas.repository.SysBlockChainDOMapper;
import cn.tpson.kulu.gas.service.BaseService;
import cn.tpson.kulu.gas.util.BeanUtils;
import cn.tpson.kulu.gas.util.MessageDigestUtils;
import cn.tpson.kulu.gas.util.RequestContextUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public abstract class BaseServiceImpl<DTO, DO, QUERY> implements BaseService<DTO, DO, QUERY> {
    @Autowired
    private SysBlockChainDOMapper sysBlockChainDOMapper;
    @Autowired
    private SysBlockChainContentDOMapper sysBlockChainContentDOMapper;
    @Autowired
    private SysUserCache sysUserCache;

    @Override
    public SysUserDTO getUser() {
        String sid = RequestContextUtils.getValue(SysUserDTO.SID);
        return sysUserCache.get(sid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(DTO record) {
        Class<DO> clazz = getGenericClassForDO();
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
        return mapper().updateByPrimaryKey(BeanUtils.copyProperties(getGenericClassForDO(), record));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateByIds(QUERY query) {
        return mapper().updateByPrimaryKeys(query);
    }

    @Override
    public DTO getById(Integer id) {
        DO record = mapper().selectByPrimaryKey(id);
        return BeanUtils.copyProperties(getGenericClassForDTO(), record);
    }

    @Override
    public List<DTO> list() {
        List<DO> list =  mapper().selectAll();
        return BeanUtils.copyPropertiesForList(getGenericClassForDTO(), list);
    }

    @Override
    public List<DTO> listByExample(QUERY example) {
        List<DO> list = mapper().selectByExample(example);
        return BeanUtils.copyPropertiesForList(getGenericClassForDTO(), list);
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

    /**
     * 生成数据区块链.
     * @param record
     * @param source 数据来源；1：用户信息；2：检测信息；3：装机申请；4：拆机申请；
     * @param contentCreator
     * @param gmtContentModified
     * @param sid
     * @return
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    @Override
    public int createBlockChain(DTO record, Short source, String contentCreator, Instant gmtContentModified, Integer sid) {
        Instant now = Instant.now();
        String content = JSON.toJSONString(record);
        SysBlockChainContentDO sysBlockChainContentDO = new SysBlockChainContentDO(Boolean.FALSE, now, null);
        sysBlockChainContentDO.setContent(content);
        sysBlockChainContentDOMapper.insert(sysBlockChainContentDO);
        Integer contentId = sysBlockChainContentDO.getId();

        String lastBlockHash = sysBlockChainDOMapper.selectLastBlockHash();
        SysBlockChainDO sysBlockChainDO = new SysBlockChainDO(Boolean.FALSE, now, null);
        String contentHash = MessageDigestUtils.MD5(content);
        sysBlockChainDO.setContentHash(contentHash);
        sysBlockChainDO.setLastBlockHash(lastBlockHash);
        sysBlockChainDO.setContentCreator(contentCreator);
        sysBlockChainDO.setGmtContentModified(gmtContentModified);
        sysBlockChainDO.setContentId(contentId);
        sysBlockChainDO.setSource(source.shortValue());
        sysBlockChainDO.setSid(sid);
        Integer blockId = sysBlockChainDOMapper.insert(sysBlockChainDO);

        lastBlockHash = lastBlockHash == null ? "" : lastBlockHash;
        sysBlockChainDO.setBlockHash(MessageDigestUtils.SHA256(lastBlockHash + contentHash + blockId));
        sysBlockChainDOMapper.updateByPrimaryKey(sysBlockChainDO);
        return blockId;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////
    protected Class<DTO> getGenericClassForDTO() {
        return getGenericClass(0);
    }

    protected Class<DO> getGenericClassForDO() {
        return getGenericClass(1);
    }

    @SuppressWarnings("unchecked")
    protected <T> Class<T> getGenericClass(int idx) {
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[idx];
        return (Class<T>)type;
    }
}
