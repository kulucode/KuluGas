package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysRoleDO;
import cn.tpson.kulu.gas.dto.SysRoleDTO;
import cn.tpson.kulu.gas.query.SysRoleQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysRoleDOMapper;
import cn.tpson.kulu.gas.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/05
 */
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDTO, SysRoleDO, SysRoleQuery> implements SysRoleService {
    @Autowired
    private SysRoleDOMapper sysRoleDOMapper;

    @Override
    public BaseMapper<SysRoleQuery, SysRoleDO> mapper() {
        return sysRoleDOMapper;
    }
}
