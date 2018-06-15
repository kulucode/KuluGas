package cn.tpson.kulu.gas.intercepter;

import cn.tpson.kulu.gas.cache.SysUserCache;
import cn.tpson.kulu.gas.dto.SysUserDTO;
import cn.tpson.kulu.gas.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhangka in 2018/06/14
 * 登录状态验证.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SysUserCache sysUserCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sid = CookieUtils.getCookie(request, SysUserDTO.TOKEN_NAME);
        if (StringUtils.isBlank(sid)) {
            response.sendRedirect("/login.html");
            return false;
        }

        SysUserDTO sysUserDTO = sysUserCache.get(sid);
        if (sysUserDTO == null) {
            response.sendRedirect("/login.html");
            return false;
        }
        if (sysUserDTO.getDeleted() || sysUserDTO.getStatus() != SysUserDTO.STATUS_NORMAL) {
            throw new RuntimeException("账号状态异常.");
        }

        return true;
    }
}
