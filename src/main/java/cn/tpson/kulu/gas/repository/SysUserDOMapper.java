package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysUserDO;
import cn.tpson.kulu.gas.query.SysUserQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDOMapper extends BaseMapper<SysUserQuery, SysUserDO> {
    SysUserDO getByUsername(String username);
}