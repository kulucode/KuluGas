package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.SysRoleDO;
import cn.tpson.kulu.gas.dto.SysRoleDTO;
import cn.tpson.kulu.gas.query.SysRoleQuery;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface SysRoleService extends BaseService<SysRoleDTO, SysRoleDO, SysRoleQuery> {
    void initSysMenu(SysRoleDTO sysRoleDTO);

    List<SysRoleDTO> getByUserId(Integer uid);
}
