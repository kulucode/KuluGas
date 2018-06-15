package cn.tpson.kulu.gas.service;

import cn.tpson.kulu.gas.domain.SysMenuDO;
import cn.tpson.kulu.gas.dto.SysMenuDTO;
import cn.tpson.kulu.gas.query.SysMenuQuery;

import java.util.List;

/**
 * Created by Zhangka in 2018/06/05
 */
public interface SysMenuService extends BaseService<SysMenuDTO, SysMenuDO, SysMenuQuery> {
    List<SysMenuDTO> getByRoleId(Integer rid);

    List<SysMenuDTO> getByUserId(Integer uid);
}
