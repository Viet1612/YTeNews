/**
 * Copyright(C) 2019 Tran Thanh Viet
 * BaseDaoImpl.java, Oct 9, 2019 VietTT
 */
package ytebnews.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ytebnews.dao.BaseDao;
import ytebnews.utils.DatabaseProperties;

/**
 * @author VietTT
 *
 */
public class BaseDaoImpl implements BaseDao {
	protected Connection con = null;
	protected PreparedStatement pst = null;
	protected ResultSet rs = null;

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.BaseDao#connectDB()
	 */
	@Override
	public void connectDB() throws SQLException, ClassNotFoundException {
		try {
			// Lấy thông tin DB
			String url = DatabaseProperties.getInfoDB("url") + DatabaseProperties.getInfoDB("databasename")
					+ DatabaseProperties.getInfoDB("charset");
			String user = DatabaseProperties.getInfoDB("user");
			String password = DatabaseProperties.getInfoDB("pass");
			Class.forName(DatabaseProperties.getInfoDB("driver"));
			// Kết nối DB
			con = DriverManager.getConnection(url, user, password);

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		}

	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.BaseDao#closeConnectDB()
	 */
	@Override
	public void closeConnectDB() throws SQLException {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		}

	}

}
