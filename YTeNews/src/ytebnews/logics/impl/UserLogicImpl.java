/**
 * Copyright(C) 2019 Tran Thanh Viet
 * UserLogicImpl.java, Oct 13, 2019 VietTT
 */
package ytebnews.logics.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import ytebnews.dao.UserDao;
import ytebnews.dao.impl.UserDaoImpl;
import ytebnews.entities.User;
import ytebnews.logics.UserLogic;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;

/**
 * @author VietTT
 *
 */
public class UserLogicImpl implements UserLogic {

	private UserDao userDao = new UserDaoImpl();

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.logics.UserLogic#existUser(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existUser(String loginName, String pass)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		User user = userDao.getUserByLoginName(loginName);
		if (user != null) {
			String passEncrypt = Common.encryptPass(pass, user.getSalt());
			if (passEncrypt.equals(user.getPass())) {
				return true;
			}
		}
		return false;
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.UserLogic#getUserByLoginName(java.lang.String, int)
	 */
	@Override
	public User getUserByLoginName(String loginName) throws SQLException, ClassNotFoundException {
		User user = userDao.getUserByLoginName(loginName);
		return user;
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.UserLogic#checkUserIdAuthor(int)
	 */
	@Override
	public boolean checkUserIdAuthor(int userId) throws ClassNotFoundException, SQLException {
		return userDao.checkUserId(userId, Constant.RULE_AUTHOR);
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.UserLogic#getListUser(int, int, java.lang.String)
	 */
	@Override
	public List<User> getListUser(int offset, int limit, String keyName) throws SQLException, ClassNotFoundException {
		return userDao.getListUser(offset, limit, keyName);
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.UserLogic#getTotalUser(java.lang.String)
	 */
	@Override
	public int getTotalUser(String keyName) throws SQLException, ClassNotFoundException {
		return userDao.getTotalUser(keyName);
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.UserLogic#insertUser(ytebnews.entities.User)
	 */
	@Override
	public void insertUser(User user) throws SQLException, ClassNotFoundException {
		userDao.insertUser(user);
		
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.UserLogic#checkExistLoginName(java.lang.String)
	 */
	@Override
	public boolean checkExistLoginName(String loginName) throws ClassNotFoundException, SQLException {
		boolean isExist = false;
		if (userDao.getUserByLoginName(loginName) != null) {
			isExist = true;
		}
		return isExist;
	}

}
