package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.cache.SysUserCache;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.util.RequestContextUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * Created by Zhangka in 2018/06/15
 */
public class BaseController {
    @Autowired
    private SysUserCache sysUserCache;

    public SysUserDTO getUser() {
        String sid = getSid();
        return sysUserCache.get(sid);
    }

    public String getSid() {
        return RequestContextUtils.getValue(SysUserDTO.SID);
    }

    public Integer getUid() {
        return getUser().getId();
    }
}
