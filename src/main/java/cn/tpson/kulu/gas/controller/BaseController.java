package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.cache.SysUserCache;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.util.CookieUtils;
import cn.tpson.kulu.gas.util.RequestContextUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zhangka in 2018/06/15
 */
public class BaseController {
    @Autowired
    private SysUserCache sysUserCache;

    public SysUserDTO getUser() {
        HttpServletRequest request = RequestContextUtils.getRequest();
        String sid = CookieUtils.getCookie(request, SysUserDTO.TOKEN_NAME);
        return sysUserCache.get(sid);
    }
}
