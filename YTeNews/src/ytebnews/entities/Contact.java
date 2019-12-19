/**
 * Copyright(C) 2019 Tran Thanh Viet
 * Contact.java, Oct 9, 2019 VietTT
 */
package ytebnews.entities;

/**
 * @author VietTT
 *
 */
public class Contact {

	private int contactId;
	private String userName;
	private String email;
	private String tel;
	private String message;
	private String dateContact;

	/**
	 * @return the dateContact
	 */
	public String getDateContact() {
		return dateContact;
	}

	/**
	 * @param dateContact the dateContact to set
	 */
	public void setDateContact(String dateContact) {
		this.dateContact = dateContact;
	}

	/**
	 * @return the contactId
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
