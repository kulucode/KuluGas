package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.SysUserDO;
import cn.tpson.kulu.gas.dto.SysRoleDTO;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.dto.SysUserPersonalDTO;
import cn.tpson.kulu.gas.query.SysUserQuery;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface SysUserService extends BaseService<SysUserDTO, SysUserDO, SysUserQuery> {
    void initBuildingSite(SysUserDTO sysUserDTO);

    void initPersonal(SysUserDTO sysUserDTO);

    void initSysRole(SysUserDTO sysUserDTO);

    void init(SysUserDTO sysUserDTO);

    SysUserDTO getByUsername(String username);
}
