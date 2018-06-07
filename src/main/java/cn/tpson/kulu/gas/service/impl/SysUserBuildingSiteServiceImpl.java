package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysUserBuildingSiteDO;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.query.SysUserBuildingSiteQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysUserBuildingSiteDOMapper;
import cn.tpson.kulu.gas.service.SysUserBuildingSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Zhangka in 2018/06/05
 */
@Service
@Transactional(readOnly = true)
public class SysUserBuildingSiteServiceImpl extends BaseServiceImpl<SysUserBuildingSiteDTO, SysUserBuildingSiteDO, SysUserBuildingSiteQuery> implements SysUserBuildingSiteService {
    @Autowired
    private SysUserBuildingSiteDOMapper sysUserBuildingSiteDOMapper;

    @Override
    public BaseMapper<SysUserBuildingSiteQuery, SysUserBuildingSiteDO> mapper() {
        return sysUserBuildingSiteDOMapper;
    }
}
