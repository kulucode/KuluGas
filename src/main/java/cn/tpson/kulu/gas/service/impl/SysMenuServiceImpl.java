package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysMenuDO;
import cn.tpson.kulu.gas.dto.SysMenuDTO;
import cn.tpson.kulu.gas.query.SysMenuQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysMenuDOMapper;
import cn.tpson.kulu.gas.service.SysMenuService;
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
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDTO, SysMenuDO, SysMenuQuery> implements SysMenuService {
    @Autowired
    private SysMenuDOMapper sysMenuDOMapper;

    @Override
    public BaseMapper<SysMenuQuery, SysMenuDO> mapper() {
        return sysMenuDOMapper;
    }

    @Override
    public List<SysMenuDTO> getByRoleId(Integer rid) {
        List<SysMenuDO> list = sysMenuDOMapper.selectByRoleId(rid);
        return BeanUtils.copyPropertiesForList(SysMenuDTO.class, list);
    }

    @Override
    public List<SysMenuDTO> getByUserId(Integer uid) {
        List<SysMenuDO> list = sysMenuDOMapper.selectByUserId(uid);
        return BeanUtils.copyPropertiesForList(SysMenuDTO.class, list);
    }
}
