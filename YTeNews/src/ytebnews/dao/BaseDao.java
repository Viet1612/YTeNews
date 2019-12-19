/**
 * Copyright(C) 2019 Tran Thanh Viet
 * BaseDao.java, Oct 9, 2019 VietTT
 */
package ytebnews.dao;

import java.sql.SQLException;

/**
 * @author VietTT
 *
 */
public interface BaseDao {
	
	/**
	 * Tạo kết nối đến DB
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 */
	public void connectDB() throws SQLException, ClassNotFoundException;

	/**
	 * Đóng kết nối với DB
	 * 
	 * @param con
	 */
	public void closeConnectDB() throws SQLException;


}
