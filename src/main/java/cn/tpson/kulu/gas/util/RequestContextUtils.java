package cn.tpson.kulu.gas.util;

import cn.tpson.kulu.gas.dto.SysUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RequestContextUtils {
	private RequestContextUtils() {
		throw new AssertionError("No cn.tpson.kulu.gas.util.RequestContextUtils instances for you!");
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return servletRequestAttributes.getRequest();
	}

	public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    public static String getValue(String name) {
        HttpServletRequest request = getRequest();
		String value = request.getHeader(name);

		if (StringUtils.isBlank(value)) {
			value = request.getParameter(name);
		}
		if (StringUtils.isBlank(value)) {
			value = CookieUtils.getCookie(request, name);
		}

		return value;
	}
}
