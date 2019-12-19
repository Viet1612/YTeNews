/**
 * Copyright(C) 2019 Luvina Software Company
 * DatabaseProperties.java, Aug 2, 2019 VietTT
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
 * Lấy thông tin DB từ file
 * 
 * @author VietTT
 *
 */
public class DatabaseProperties {

	private static Map<String, String> infoDbMap = new HashMap<String, String>();
	/**
	 * Đọc thông tin DB từ file properties lưu giữa liệu vào dataDB
	 */
	static {
		Properties prop = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			// Load file properties
			prop.load(new InputStreamReader(classLoader.getResourceAsStream(Constant.DATABASE_PROP), Charset.forName(Constant.UTF8)));
			// Lưu key vào danh sách
			@SuppressWarnings("unchecked")
			Enumeration<String> enu = (Enumeration<String>) prop.propertyNames();
			while (enu.hasMoreElements()) {
				// lẤY key
				String key = enu.nextElement();
				// Truyền key, value vào hashmap
				infoDbMap.put(key, prop.getProperty(key));

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
	public static String getInfoDB(String key) {
		String value = "";
		if (infoDbMap.containsKey(key)) {
			value = infoDbMap.get(key);
		}
		return value;
	}

}
