package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysRoleDO;
import cn.tpson.kulu.gas.dto.SysRoleDTO;
import cn.tpson.kulu.gas.query.SysRoleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDOMapper extends BaseMapper<SysRoleQuery, SysRoleDO> {
    List<SysRoleDO> selectByUserId(Integer uid);
}