package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysUserBuildingSiteDO;
import cn.tpson.kulu.gas.query.SysUserBuildingSiteQuery;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserBuildingSiteDOMapper extends BaseMapper<SysUserBuildingSiteQuery, SysUserBuildingSiteDO> {
    SysUserBuildingSiteDO selectByUserId(Integer uid);
}