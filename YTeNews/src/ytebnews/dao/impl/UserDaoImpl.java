/**
 * Copyright(C) 2019 Tran Thanh Viet
 * UserDaoImpl.java, Oct 13, 2019 VietTT
 */
package ytebnews.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ytebnews.dao.UserDao;
import ytebnews.entities.User;
import ytebnews.utils.Constant;

/**
 * @author VietTT
 *
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.UserDao#getUserByLoginName(java.lang.String)
	 */
	@Override
	public User getUserByLoginName(String loginName) throws SQLException, ClassNotFoundException {
		User user = null;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT * ");
				sqlQuery.append("FROM tbl_user ");
				sqlQuery.append("WHERE BINARY login_name = ?");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setString(++index, loginName);
				rs = pst.executeQuery();
				// Lấy bản ghi
				while (rs.next()) {
					user = new User();
					user.setLoginName(rs.getString(Constant.T_LOGIN_NAME));
					user.setPass(rs.getString(Constant.T_PASS));
					user.setSalt(rs.getString(Constant.T_SALT));
					user.setRule(rs.getInt(Constant.T_RULE));
					user.setUserId(rs.getInt(Constant.T_USER_ID));
					user.setFullName(rs.getString(Constant.T_FUll_NAME));
				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return user;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.UserDao#checkUserId(int)
	 */
	@Override
	public boolean checkUserId(int userId, int rule) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT user_id ");
				sqlQuery.append("FROM tbl_user ");
				sqlQuery.append("WHERE user_id = ? ");
				sqlQuery.append("AND rule = ?");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setInt(++index, userId);
				pst.setInt(++index, rule);
				rs = pst.executeQuery();
				// Lấy bản ghi
				if (rs.next()) {
					check = true;
				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

		return check;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.UserDao#getListUser(int, int, java.lang.String)
	 */
	@Override
	public List<User> getListUser(int offset, int limit, String keyName) throws SQLException, ClassNotFoundException {
		List<User> listUser = null;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT * ");
				sqlQuery.append("FROM tbl_user ");
				sqlQuery.append("WHERE rule = ? ");
				if (keyName.trim().length() > 0) {
					sqlQuery.append("AND full_name = ? ");
				}
				sqlQuery.append("ORDER BY full_name ASC, user_id ASC ");
				sqlQuery.append("LIMIT ?, ?;");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setInt(++index, Constant.RULE_AUTHOR);
				if (keyName.trim().length() > 0) {
					pst.setString(++index, keyName);
				}
				pst.setInt(++index, offset);
				pst.setInt(++index, limit);
				rs = pst.executeQuery();
				listUser = new ArrayList<User>();
				// Lấy bản ghi
				while (rs.next()) {
					User user = new User();
					user.setLoginName(rs.getString(Constant.T_LOGIN_NAME));
					user.setEmail(rs.getString("email"));
					user.setTel(rs.getString("tel"));
					user.setLoginName(rs.getString(Constant.T_LOGIN_NAME));
					user.setPass(rs.getString(Constant.T_PASS));
					user.setSalt(rs.getString(Constant.T_SALT));
					user.setRule(rs.getInt(Constant.T_RULE));
					user.setUserId(rs.getInt(Constant.T_USER_ID));
					user.setFullName(rs.getString(Constant.T_FUll_NAME));
					listUser.add(user);
				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return listUser;
	}

	/* (non-javadoc)
	 * @see ytebnews.dao.UserDao#getTotalUser(java.lang.String)
	 */
	@Override
	public int getTotalUser(String keyName) throws SQLException, ClassNotFoundException {
		int total = 0;
		try {
			connectDB();
			if (con != null) {
				// Câu SQL
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT count(*) AS total ");
				sqlQuery.append("FROM tbl_user ");
				sqlQuery.append("WHERE rule = ? ");
				if (keyName.trim().length() > 0) {
					sqlQuery.append("AND full_name = ? ");
				}
				// Trường hợp có chọn group id
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setInt(++index, Constant.RULE_AUTHOR);
				if (keyName.trim().length() > 0) {
					pst.setString(++index, keyName);
				}
				rs = pst.executeQuery();
				// Lấy tổng số bản ghi
				while (rs.next()) {
					total = rs.getInt("total");
				}

			}
		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

		return total;
	}

	/* (non-javadoc)
	 * @see ytebnews.dao.UserDao#insertUser(ytebnews.entities.User)
	 */
	@Override
	public void insertUser(User user) throws SQLException, ClassNotFoundException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"INSERT INTO tbl_user (login_name, full_name, email, tel, pass, salt, rule)");
			sqlQuery.append("VALUE (?, ?, ?, ?, ?, ?, ?)");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			// Lấy lại key userId tự tăng khi insert
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setString(++index, user.getLoginName());
			pst.setString(++index, user.getFullName());
			pst.setString(++index, user.getEmail());
			pst.setString(++index, user.getTel());
			pst.setString(++index, user.getPass());
			pst.setString(++index, user.getSalt());
			pst.setInt(++index, user.getRule());
			pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		
	}

}
