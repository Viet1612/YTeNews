/**
 * Copyright(C) 2019 Tran Thanh Viet
 * MessageProperties.java, Oct 13, 2019 VietTT
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
public class MessageProperties {
	
	private static Map<String, String> messageMap = new HashMap<String, String>();
	/**
	 * Đọc thông tin DB từ file properties lưu giữa liệu vào MESSGA
	 */
	static {
		Properties prop = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			// Load file properties
			prop.load(new InputStreamReader(classLoader.getResourceAsStream(Constant.MESS_PROP), Charset.forName(Constant.UTF8)));
			// Lưu key vào danh sách
			@SuppressWarnings("unchecked")
			Enumeration<String> enu = (Enumeration<String>) prop.propertyNames();
			while (enu.hasMoreElements()) {
				//Lấy key
				String key = enu.nextElement();
				//Truyền key, value vào hashmap
				messageMap.put(key, prop.getProperty(key));

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
	public static String getMesage(String key) {
		String value = "";
		if (messageMap.containsKey(key)) {
			value = messageMap.get(key);
		}
		return value;
	}
	
	public static void main(String[] args) {
		
		System.out.println(MessageProperties.getMesage(Constant.ER016));
	}

}
