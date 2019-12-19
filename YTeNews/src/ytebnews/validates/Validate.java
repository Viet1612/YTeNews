/**
 * Copyright(C) 2019 Tran Thanh Viet
 * Validate.java, Dec 14, 2019 VietTT
 */
package ytebnews.validates;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ytebnews.entities.Contact;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * @author VietTT
 *
 */
public class Validate {

	/**
	 * Validate thông tin nhập
	 * 
	 * @param userInfor
	 * @return listErrMes danh sách lỗi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> validateContact(Contact contact) {
		List<String> listErrMes = new ArrayList<String>();
		// user namw
		String userNameErr = validateName(contact.getUserName());
		Common.addErr(listErrMes, userNameErr);
		// email
		String emailErr = validateEmail(contact.getEmail());
		Common.addErr(listErrMes, emailErr);
		// TEL
		String telErr = validateTel(contact.getTel());
		Common.addErr(listErrMes, telErr);
		// user namw
		String messErr = validateMess(contact.getMessage());
		Common.addErr(listErrMes, messErr);

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
	private String validateName(String userName) {
		String errMes = "";
		if (!Common.checkEmpty(userName)) { // Check đã nhập
			errMes = MessageProperties.getMesage(Constant.ER001_USERNAME);
			// Check length
		}
		return errMes;
	}
	
	private String validateMess(String mess) {
		String errMes = "";
		if (!Common.checkEmpty(mess)) { // Check đã nhập
			errMes = MessageProperties.getMesage(Constant.ER001_MESS);
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
	private String validateEmail(String email) {
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
		} else if (!Common.checkFormat(tel, Constant.FORMAT_TEL)) {
			errMes = MessageProperties.getMesage(Constant.ER005_TEL);
		}
		return errMes;
	}

}
