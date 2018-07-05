package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysBlockChainDO;
import cn.tpson.kulu.gas.query.SysBlockChainQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBlockChainDOMapper extends BaseMapper<SysBlockChainQuery, SysBlockChainDO> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBlockChainDO record);

    SysBlockChainDO selectByPrimaryKey(Integer id);

    List<SysBlockChainDO> selectAll();

    int updateByPrimaryKey(SysBlockChainDO record);

    String selectLastBlockHash();

    SysBlockChainDO selectOneByExample(SysBlockChainQuery query);
}