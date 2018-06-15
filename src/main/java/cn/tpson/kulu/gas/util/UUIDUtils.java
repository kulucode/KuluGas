package cn.tpson.kulu.gas.util;

import java.util.UUID;

/**
 *
 */
public class UUIDUtils {
	private UUIDUtils() {
		throw new AssertionError("No cn.tpson.kulu.gas.util.UUIDUtils instances for you!");
	}
	
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
