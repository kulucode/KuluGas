package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.domain.SysUserDO;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.dto.SysUserPersonalDTO;
import cn.tpson.kulu.gas.query.SysUserQuery;
import cn.tpson.kulu.gas.repository.BaseMapper;
import cn.tpson.kulu.gas.repository.SysUserDOMapper;
import cn.tpson.kulu.gas.service.SysRoleService;
import cn.tpson.kulu.gas.service.SysUserBuildingSiteService;
import cn.tpson.kulu.gas.service.SysUserPersonalService;
import cn.tpson.kulu.gas.service.SysUserService;
import cn.tpson.kulu.gas.util.BeanUtils;
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
    @Autowired
    private SysUserBuildingSiteService sysUserBuildingSiteService;
    @Autowired
    private SysUserPersonalService sysUserPersonalService;
    @Autowired
    private SysRoleService sysRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(SysUserDTO sysUserDTO) {
        int id = super.insert(sysUserDTO);
        if (id > 0) {
            SysUserPersonalDTO sysUserPersonalDTO = sysUserDTO.getPersonal();
            if (sysUserPersonalDTO != null) {
                sysUserPersonalDTO.setUid(id);
                sysUserPersonalService.insert(sysUserPersonalDTO);
            }

            SysUserBuildingSiteDTO sysUserBuildingSiteDTO = sysUserDTO.getBuildingSite();
            if (sysUserBuildingSiteDTO != null) {
                sysUserBuildingSiteDTO.setUid(id);
                sysUserBuildingSiteService.insert(sysUserBuildingSiteDTO);
            }
        }

        return id;
    }

    @Override
    public void initBuildingSite(SysUserDTO sysUserDTO) {
        Integer uid = sysUserDTO.getId();
        if (uid != null) {
            sysUserDTO.setBuildingSite(sysUserBuildingSiteService.getByUserId(uid));
        }
    }

    @Override
    public void initPersonal(SysUserDTO sysUserDTO) {
        Integer uid = sysUserDTO.getId();
        if (uid != null) {
            sysUserDTO.setPersonal(sysUserPersonalService.getByUserId(uid));
        }
    }

    @Override
    public void initSysRole(SysUserDTO sysUserDTO) {
        Integer uid = sysUserDTO.getId();
        if (uid != null) {
            sysUserDTO.setSysRoles(sysRoleService.getByUserId(uid));
        }
    }

    @Override
    public void init(SysUserDTO sysUserDTO) {
        initBuildingSite(sysUserDTO);
        initPersonal(sysUserDTO);
        initSysRole(sysUserDTO);
    }

    @Override
    public SysUserDTO getByUsername(String username) {
        return BeanUtils.copyProperties(SysUserDTO.class, sysUserDOMapper.getByUsername(username));
    }

    @Override
    public BaseMapper<SysUserQuery, SysUserDO> mapper() {
        return sysUserDOMapper;
    }
}
