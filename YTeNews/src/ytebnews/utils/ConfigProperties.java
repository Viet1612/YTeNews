/**
 * Copyright(C) 2019 Tran Thanh Viet
 * Common.java, Oct 12, 2019 VietTT
 */
package ytebnews.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author VietTT
 *
 */
public class ConfigProperties {

	private static Map<String, String> configMap = new HashMap<String, String>();
	/**
	 * Đọc các cấu hình từ file properties
	 */
	static {
		Properties prop = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			// Load file properties
			prop.load(new InputStreamReader(classLoader.getResourceAsStream(Constant.CONFIG_PROP), Charset.forName(Constant.UTF8)));
			// Lưu key vào danh sách
			@SuppressWarnings("unchecked")
			Enumeration<String> enu = (Enumeration<String>) prop.propertyNames();
			while (enu.hasMoreElements()) {
				// Lấy key
				String key = enu.nextElement();
				// Truyền key, value vào hashmap
				configMap.put(key, prop.getProperty(key));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Lấy giá trị tương ứng của mỗi key
	 * 
	 * @param key
	 * @return value giá trị key tương ứng
	 */
	public static String getConfig(String key) {
		String value = "";
		if (configMap.containsKey(key)) {
			value = configMap.get(key);
		}
		return value;
	}

}
