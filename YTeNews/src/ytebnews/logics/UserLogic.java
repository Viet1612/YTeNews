/**
 * Copyright(C) 2019 Tran Thanh Viet
 * UserLogic.java, Oct 13, 2019 VietTT
 */
package ytebnews.logics;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import ytebnews.entities.User;

/**
 * @author VietTT
 *
 */
public interface UserLogic {

	/**
	 * Xác định user có tồn tại trong db không
	 * 
	 * @param loginName tên đăng nhập ng dùng nhập
	 * @param pass      mật khẩu ng dùng nhập
	 * @param rule      phân cấp user
	 * @return true tên đăng nhập và mật khẩu đúng, false tên đăng nhập hoặc pass
	 *         sai
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public boolean existUser(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException;

	/**
	 * Lấy user từ tbl_user với login nhập ở form
	 * 
	 * @param loginName tên đăng nhập
	 * @param rule      phân cấp user
	 * @return User
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public User getUserByLoginName(String loginName) throws SQLException, ClassNotFoundException;

	/**
	 * Check tồn tại của userId
	 * 
	 * @param userId , rule
	 * @return true tồn tại
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean checkUserIdAuthor(int userId) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy list user
	 * @param offset
	 * @param limit
	 * @param keyName
	 * @return listUsser
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public List<User> getListUser(int offset, int limit, String keyName) throws SQLException, ClassNotFoundException;
	
	/**
	 * Lấy tổng user
	 * @param keyname
	 * @return tổng số user
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public int getTotalUser(String keyName) throws SQLException, ClassNotFoundException;
	
	/**
	 * Insert User
	 * @param user
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void insertUser(User user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException;
	
	/**
	 * Check tồn tại 
	 * @param loginName
	 * @return true đã tồn tại
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean checkExistLoginName(String loginName) throws ClassNotFoundException, SQLException;

}
