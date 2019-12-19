package ytebnews.dao;

import java.sql.SQLException;

import ytebnews.entities.Contact;

public interface ContactDao {
	
	/**
	 * Insert Contact
	 * @param contact
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException;

}
