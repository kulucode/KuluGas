package cn.tpson.kulu.gas.util;

import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	private JSONObject json;

	private JsonUtils() {
		throw new AssertionError("No cn.tpson.kulu.gas.util.JsonUtils instances for you!");
	}

	private JsonUtils(JSONObject json) {
		this.json = json;
	}

	public static JsonUtils builder() {
		return new JsonUtils(new JSONObject());
	}

	public JSONObject build() {
		return this.json;
	}

	public JsonUtils put(String key, Object value) {
		this.json.put(key, value);
		return this;
	}
}
