/**
 * Copyright(C) 2019 Tran Thanh Viet
 * ValidateUser.java, Oct 13, 2019 VietTT
 */
package ytebnews.validates;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ytebnews.entities.User;
import ytebnews.logics.UserLogic;
import ytebnews.logics.impl.UserLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * @author VietTT
 *
 */
public class ValidateUser {

	private UserLogic userLogic = new UserLogicImpl();

	/**
	 * Validate cho loginName và pass
	 * 
	 * @param loginName
	 * @param pass
	 * @return Message danh sách các lỗi
	 */
	public String validateLoginAdmin(String loginName, String pass)
			throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
		String errMes = "";
		User user = userLogic.getUserByLoginName(loginName);
		if (!userLogic.existUser(loginName, pass) || user.getRule() == Constant.RULE_USER) {
			// kiểm tra loginname, pass đúng không sai add lỗi vào errMes
			errMes = MessageProperties.getMesage(Constant.ER016);
		}
		return errMes;

	}

	/**
	 * Validate thông tin nhập
	 * 
	 * @param user
	 * @return listErrMes danh sách lỗi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> validateUserInfor(User user) throws ClassNotFoundException, SQLException {
		List<String> listErrMes = new ArrayList<String>();
		// Login Name
		System.out.println(user.getLoginName());
		String loginNameErr = validateLoginName(user.getLoginName());
		System.out.println(loginNameErr);
		Common.addErr(listErrMes, loginNameErr);

		// fullname
		String fullNameErr = validateFullName(user.getFullName());
		Common.addErr(listErrMes, fullNameErr);

		// email
		String emailErr = validateEmail(user.getEmail(), user.getUserId());
		Common.addErr(listErrMes, emailErr);

		// TEL
		String telErr = validateTel(user.getTel());
		Common.addErr(listErrMes, telErr);
		// Pass
		String passErr = validatePass(user.getPass());
		Common.addErr(listErrMes, passErr);

		// PassCon
		String passConfErr = validatePassConf(user.getPass(), user.getPassConf());
		Common.addErr(listErrMes, passConfErr);
		// Code Level

		return listErrMes;
	}

	/**
	 * Validate login name
	 * 
	 * @param loginName
	 * @return danh sách các lỗi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private String validateLoginName(String loginName) throws ClassNotFoundException, SQLException {
		String errMes = "";
		if (!Common.checkEmpty(loginName)) { // Check đã nhập
			errMes = MessageProperties.getMesage(Constant.ER001_LOGNAME);
			// Check length
		} else if (!Common.checkFormat(loginName, Constant.FORMAT_LOGIN_NAME)) { // Check đúng định dạng
			errMes = MessageProperties.getMesage(Constant.ER019_LOGIN_NAME);
		} else if (!Common.checkLength(Constant.MIN_LENGTH_LOGIN_NAME, Constant.MAX_LENGTH_LOGIN_NAME, loginName)) {
			errMes = MessageProperties.getMesage(Constant.ER007_LOGIN_NAME);
		} else if (userLogic.checkExistLoginName(loginName)) { // Check tồn tại
			errMes = MessageProperties.getMesage(Constant.ER003_LOGIN_NAME);
		}

		return errMes;
	}

	/**
	 * Validate full name
	 * 
	 * @param fullName
	 * @return danh sách lỗi
	 */
	private String validateFullName(String fullName) {
		String errMes = "";
		// Check đã nhập
		if (!Common.checkEmpty(fullName)) {
			errMes = MessageProperties.getMesage(Constant.ER001_FULL_NAME);
			// Check length
		}
		return errMes;
	}

	/**
	 * Validate email
	 * 
	 * @param email
	 * @return danh sách lỗi
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private String validateEmail(String email, int userId) throws ClassNotFoundException, SQLException {
		String errMes = "";
		// Check đã nhập
		if (!Common.checkEmpty(email)) {
			errMes = MessageProperties.getMesage(Constant.ER001_EMAIL);
			// Check length
		} else if (!Common.checkFormat(email, Constant.FORMAT_EMAIL)) {
			errMes = MessageProperties.getMesage(Constant.ER005_EMAIL);
			// Check tồn tại
		}

		return errMes;
	}

	/**
	 * Validate số điện thoại
	 * 
	 * @param TEL
	 * @return danh sách lỗi
	 */
	private String validateTel(String tel) {
		String errMes = "";
		// Check đã nhập
		if (!Common.checkEmpty(tel)) {
			errMes = MessageProperties.getMesage(Constant.ER001_TEL);
			// check length
		} else if (!Common.checkFormat(tel, Constant.FORMAT_TEL)) {
			errMes = MessageProperties.getMesage(Constant.ER005_TEL);
		}
		return errMes;
	}

	/**
	 * Validate mật khẩu
	 * 
	 * @param pass
	 * @return danh sách lỗi
	 */
	private String validatePass(String pass) {
		String errMes = "";
		// Check đã nhập
		if (!Common.checkEmpty(pass)) {
			errMes = MessageProperties.getMesage(Constant.ER001_PASS);
			// Check ký tự 1 byte
		} else if (!Common.checkLength(Constant.MIN_LENGTH_PASS, Constant.MAX_LENGTH_PASS, pass)) {
			errMes = MessageProperties.getMesage(Constant.ER007_PASSWORD);
		}
		return errMes;
	}

	/**
	 * Validate mật khẩu confirm
	 * 
	 * @param passconf
	 * @return danh sách lỗi
	 */
	private String validatePassConf(String pass, String passConf) {
		String errMes = "";
		// Check nhập lại pass đúng không
		if (!Common.checkPassConfirm(pass, passConf)) {
			errMes = MessageProperties.getMesage(Constant.ER017);
		}
		return errMes;
	}

}
