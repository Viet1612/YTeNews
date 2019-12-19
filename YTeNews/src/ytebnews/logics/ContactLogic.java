/**
 * Copyright(C) 2019 Tran Thanh Viet
 * ContactLogic.java, Dec 14, 2019 VietTT
 */
package ytebnews.logics;

import java.sql.SQLException;

import ytebnews.entities.Contact;

/**
 * @author VietTT
 *
 */
public interface ContactLogic {
	
	/**
	 * Insert Contact
	 * @param contact
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException;

}
