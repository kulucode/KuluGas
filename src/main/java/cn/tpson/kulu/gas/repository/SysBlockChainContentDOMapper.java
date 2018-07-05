package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysBlockChainContentDO;
import cn.tpson.kulu.gas.dto.SysBlockChainContentDTO;
import cn.tpson.kulu.gas.query.SysBlockChainContentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysBlockChainContentDOMapper extends BaseMapper<SysBlockChainContentDTO, SysBlockChainContentDO> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysBlockChainContentDO record);

    SysBlockChainContentDO selectByPrimaryKey(Integer id);

    List<SysBlockChainContentDO> selectAll();

    int updateByPrimaryKey(SysBlockChainContentDO record);
}