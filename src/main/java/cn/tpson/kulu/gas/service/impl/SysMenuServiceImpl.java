package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysMenuDO;
import cn.tpson.kulu.gas.dto.SysMenuDTO;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysMenuDOMapper;
import cn.tpson.kulu.gas.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/05
 */
@Service
@Transactional(readOnly = true)
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDTO, SysMenuDO> implements SysMenuService {
    @Autowired
    private SysMenuDOMapper sysMenuDOMapper;

    @Override
    public BaseMapper<SysMenuDTO, SysMenuDO> mapper() {
        return sysMenuDOMapper;
    }
}
