package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysMenuDO;
import cn.tpson.kulu.gas.query.SysMenuQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuDOMapper extends BaseMapper<SysMenuQuery, SysMenuDO> {
    List<SysMenuDO> selectByRoleId(Integer rid);

    List<SysMenuDO> selectByUserId(Integer uid);
}