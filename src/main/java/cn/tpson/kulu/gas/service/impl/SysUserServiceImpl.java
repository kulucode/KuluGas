package cn.tpson.kulu.gas.service.impl;

import cn.tpson.kulu.gas.constant.UserTypeEnum;
import cn.tpson.kulu.gas.domain.SysUserDO;
import cn.tpson.kulu.gas.dto.SysBlockChainDTO;
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

import java.time.Instant;
import java.util.Objects;

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

        // 生成区块链
        Instant gmtContentModified = sysUserDTO.getGmtModified() == null ? sysUserDTO.getGmtCreate() : sysUserDTO.getGmtModified();
        createBlockChain(sysUserDTO, SysBlockChainDTO.SOURCE_USER, getUser().getUsername(), gmtContentModified, id);
        return id;
    }

    @Override
    public void initBuildingSite(SysUserDTO sysUserDTO) {
        if (sysUserDTO != null
                && Objects.equals(UserTypeEnum.USER_BUI.getType(), sysUserDTO.getType())
                && sysUserDTO.getId() != null) {
            sysUserDTO.setDetail(sysUserBuildingSiteService.getByUserId(sysUserDTO.getId()));
        }
    }

    @Override
    public void initPersonal(SysUserDTO sysUserDTO) {
        if (sysUserDTO != null
                && Objects.equals(UserTypeEnum.USER_PER.getType(), sysUserDTO.getType())
                && sysUserDTO.getId() != null) {
            sysUserDTO.setDetail(sysUserPersonalService.getByUserId(sysUserDTO.getId()));
        }
    }

    @Override
    public void initSysRole(SysUserDTO sysUserDTO) {
        if (sysUserDTO != null && sysUserDTO.getId() != null) {
            sysUserDTO.setSysRoles(sysRoleService.getByUserId(sysUserDTO.getId()));
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
    public SysUserBuildingSiteDTO getBuildingSiteByUid(Integer uid) {
        return sysUserBuildingSiteService.getByUserId(uid);
    }

    @Override
    public SysUserBuildingSiteDTO getBuildingSiteById(Integer id) {
        return sysUserBuildingSiteService.getById(id);
    }

    @Override
    public SysUserPersonalDTO getPersonal(Integer uid) {
        return sysUserPersonalService.getByUserId(uid);
    }

    @Override
    public BaseMapper<SysUserQuery, SysUserDO> mapper() {
        return sysUserDOMapper;
    }
}
