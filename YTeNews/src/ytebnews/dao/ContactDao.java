package ytebnews.dao;

import java.sql.SQLException;
import java.util.List;

import ytebnews.entities.Contact;

public interface ContactDao {

	/**
	 * Insert Contact
	 * 
	 * @param contact
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException;

	/**
	 * Lấy danh sách Contact
	 * 
	 * @return listContact
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Contact> getListContact(int offset, int limit, String keyName)
			throws SQLException, ClassNotFoundException;

	/**
	 * Lấy tổng contact
	 * 
	 * @param keyname
	 * @return tổng số contact
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getTotalContact(String keyName) throws SQLException, ClassNotFoundException;

	/**
	 * delete contact
	 * 
	 * @param contacId
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void deleteContact(int contacId) throws ClassNotFoundException, SQLException;

	/**
	 * Check tồn tại contact
	 * 
	 * @param contactId
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public boolean checkExistContact(int contactId) throws SQLException, ClassNotFoundException;
}
