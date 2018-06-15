package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysUserPersonalDO;
import cn.tpson.kulu.gas.dto.SysUserPersonalDTO;
import cn.tpson.kulu.gas.query.SysUserPersonalQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysUserPersonalDOMapper;
import cn.tpson.kulu.gas.service.SysUserPersonalService;
import cn.tpson.kulu.gas.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/05
 */
@Service
@Transactional(readOnly = true)
public class SysUserPersonalServiceImpl extends BaseServiceImpl<SysUserPersonalDTO, SysUserPersonalDO, SysUserPersonalQuery> implements SysUserPersonalService {
    @Autowired
    private SysUserPersonalDOMapper sysUserPersonalDOMapper;

    @Override
    public BaseMapper<SysUserPersonalQuery, SysUserPersonalDO> mapper() {
        return sysUserPersonalDOMapper;
    }

    @Override
    public SysUserPersonalDTO getByUserId(Integer uid) {
        SysUserPersonalDO sysUserPersonalDO = sysUserPersonalDOMapper.selectByUserId(uid);
        return BeanUtils.copyProperties(SysUserPersonalDTO.class, sysUserPersonalDO);
    }
}
