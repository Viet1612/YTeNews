/**
 * Copyright(C) 2019 Tran Thanh Viet
 * ContactLogicImpl.java, Dec 14, 2019 VietTT
 */
package ytebnews.logics.impl;

import java.sql.SQLException;
import java.util.List;

import ytebnews.dao.ContactDao;
import ytebnews.dao.impl.ContactDaoImpl;
import ytebnews.entities.Contact;
import ytebnews.logics.ContactLogic;

/**
 * @author VietTT
 *
 */
public class ContactLogicImpl implements ContactLogic {
	private ContactDao contactDao = new ContactDaoImpl();

	/* (non-javadoc)
	 * @see ytebnews.logics.ContactLogic#insertContact(ytebnews.entities.Contact)
	 */
	@Override
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException {
		contactDao.insertContact(contact);
		
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.ContactLogic#getListContact(int, int, java.lang.String)
	 */
	@Override
	public List<Contact> getListContact(int offset, int limit, String keyName)
			throws SQLException, ClassNotFoundException {
		return contactDao.getListContact(offset, limit, keyName);
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.ContactLogic#getTotalContact(java.lang.String)
	 */
	@Override
	public int getTotalContact(String keyName) throws SQLException, ClassNotFoundException {
		return contactDao.getTotalContact(keyName);
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.ContactLogic#deleteContact(int)
	 */
	@Override
	public void deleteContact(int contacId) throws ClassNotFoundException, SQLException {
		contactDao.deleteContact(contacId);
		
	}

	/* (non-javadoc)
	 * @see ytebnews.logics.ContactLogic#checkExistContact(int)
	 */
	@Override
	public boolean checkExistContact(int contactId) throws SQLException, ClassNotFoundException {
		return contactDao.checkExistContact(contactId);
	}

}
