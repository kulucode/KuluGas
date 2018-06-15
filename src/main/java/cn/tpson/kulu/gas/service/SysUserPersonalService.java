package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.SysUserPersonalDO;
import cn.tpson.kulu.gas.dto.SysUserPersonalDTO;
import cn.tpson.kulu.gas.query.SysUserPersonalQuery;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface SysUserPersonalService extends BaseService<SysUserPersonalDTO, SysUserPersonalDO, SysUserPersonalQuery> {
    SysUserPersonalDTO getByUserId(Integer uid);
}
