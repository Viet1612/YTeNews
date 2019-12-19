/**
 * Copyright(C) 2019 Tran Thanh Viet
 * ContactLogicImpl.java, Dec 14, 2019 VietTT
 */
package ytebnews.logics.impl;

import java.sql.SQLException;

import ytebnews.dao.ContactDao;
import ytebnews.dao.impl.ContactDaoImpl;
import ytebnews.entities.Contact;
import ytebnews.logics.ContactLogic;

/**
 * @author VietTT
 *
 */
public class ContactLogicImpl implements ContactLogic {
	private ContactDao ContactDao = new ContactDaoImpl();

	/* (non-javadoc)
	 * @see ytebnews.logics.ContactLogic#insertContact(ytebnews.entities.Contact)
	 */
	@Override
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException {
		ContactDao.insertContact(contact);
		
	}

}
