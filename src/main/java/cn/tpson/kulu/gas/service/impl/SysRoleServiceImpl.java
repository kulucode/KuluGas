package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysRoleDO;
import cn.tpson.kulu.gas.dto.SysRoleDTO;
import cn.tpson.kulu.gas.query.SysRoleQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysRoleDOMapper;
import cn.tpson.kulu.gas.service.SysMenuService;
import cn.tpson.kulu.gas.service.SysRoleService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDTO, SysRoleDO, SysRoleQuery> implements SysRoleService {
    @Autowired
    private SysRoleDOMapper sysRoleDOMapper;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public BaseMapper<SysRoleQuery, SysRoleDO> mapper() {
        return sysRoleDOMapper;
    }

    @Override
    public void initSysMenu(SysRoleDTO sysRoleDTO) {
        Integer rid = sysRoleDTO.getId();
        if (rid != null) {
            sysRoleDTO.setSysMenus(sysMenuService.getByRoleId(rid));
        }
    }

    @Override
    public List<SysRoleDTO> getByUserId(Integer uid) {
        List<SysRoleDO> list = sysRoleDOMapper.selectByUserId(uid);
        return BeanUtils.copyPropertiesForList(SysRoleDTO.class, list);
    }
}
