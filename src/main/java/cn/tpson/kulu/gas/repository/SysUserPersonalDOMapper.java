package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysUserPersonalDO;
import cn.tpson.kulu.gas.query.SysUserPersonalQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserPersonalDOMapper extends BaseMapper<SysUserPersonalQuery, SysUserPersonalDO> {
    SysUserPersonalDO selectByUserId(Integer uid);
}