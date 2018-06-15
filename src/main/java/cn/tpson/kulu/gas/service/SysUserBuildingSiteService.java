package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.SysUserBuildingSiteDO;
import cn.tpson.kulu.gas.dto.SysUserBuildingSiteDTO;
import cn.tpson.kulu.gas.query.SysUserBuildingSiteQuery;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface SysUserBuildingSiteService extends BaseService<SysUserBuildingSiteDTO, SysUserBuildingSiteDO, SysUserBuildingSiteQuery> {
    SysUserBuildingSiteDTO getByUserId(Integer uid);
}
