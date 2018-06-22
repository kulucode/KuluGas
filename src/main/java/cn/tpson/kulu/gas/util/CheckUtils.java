package cn.tpson.kulu.gas.util;

import cn.tpson.kulu.gas.constant.ErrorCodeEnum;
import cn.tpson.kulu.gas.exception.BusinessRuntimeException;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 抛出的异常被GlobalExceptionHandler统一捕获.
 *
 */
public class CheckUtils {
	private CheckUtils() {
		throw new AssertionError("No cn.tpson.kulu.gas.util.CheckUtils instances for you!");
	}
	
	public static boolean checkNull(Object object, ErrorCodeEnum errorCode) {
		if (object == null) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkNotNull(Object object, ErrorCodeEnum errorCode) {
		if (object != null) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkBlank(String source, ErrorCodeEnum errorCode) {
		if (StringUtils.isBlank(source)) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkEquals(Object src, Object dest, ErrorCodeEnum errorCode) {
		if (!Objects.equals(src, dest)) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkNotEquals(Object src, Object dest, ErrorCodeEnum errorCode) {
		if (Objects.equals(src, dest)) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkId(Integer id, ErrorCodeEnum errorCode) {
		if (id == null || id <= 0) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkEmpty(List<?> list, ErrorCodeEnum errorCode) {
		if (list == null || list.isEmpty()) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkTrue(Boolean bool, ErrorCodeEnum errorCode) {
		if (bool == null || !bool) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}
	
	public static boolean checkFalse(Boolean bool, ErrorCodeEnum errorCode) {
		if (bool != null && bool) {
			throw new BusinessRuntimeException(ResultVO.failResult(errorCode).toString());
		}
		
		return true;
	}

	public static boolean checkPermission(Short userType, Short type) {
        if (!Objects.equals(userType, type)) {
            throw new BusinessRuntimeException(ResultVO.failResult(ErrorCodeEnum.PERM_ERROR).toString());
        }

        return true;
    }
}
