package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysUserDO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.query.SysUserQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysUserDOMapper;
import cn.tpson.kulu.gas.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/05
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDTO, SysUserDO, SysUserQuery> implements SysUserService {
    @Autowired
    private SysUserDOMapper sysUserDOMapper;

    @Override
    public BaseMapper<SysUserQuery, SysUserDO> mapper() {
        return sysUserDOMapper;
    }
}
