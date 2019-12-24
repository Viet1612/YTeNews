package ytebnews.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ytebnews.dao.ContactDao;
import ytebnews.entities.Contact;

public class ContactDaoImpl extends BaseDaoImpl implements ContactDao {

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.ContactDao#insertContact(ytebnews.entities.Contact)
	 */
	@Override
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("INSERT INTO tbl_contact (user_name, email, tel, message, date)");
			sqlQuery.append("VALUE (?, ?, ?, ?, ?)");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			// Lấy lại key userId tự tăng khi insert
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setString(++index, contact.getUserName());
			pst.setString(++index, contact.getEmail());
			pst.setString(++index, contact.getTel());
			pst.setString(++index, contact.getMessage());
			pst.setString(++index, contact.getDateContact());
			pst.executeUpdate();

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.ContactDao#getListContact(int, int, java.lang.String)
	 */
	@Override
	public List<Contact> getListContact(int offset, int limit, String keyName)
			throws SQLException, ClassNotFoundException {
		List<Contact> listContact = null;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT * ");
				sqlQuery.append("FROM tbl_contact ");
				if (keyName.trim().length() > 0) {
					sqlQuery.append("WHERE user_name LIKE ? ");
				}
				sqlQuery.append("ORDER BY date DESC ");
				sqlQuery.append("LIMIT ?, ?;");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				if (keyName.trim().length() > 0) {
					pst.setString(++index, "%" + keyName + "%");
				}
				pst.setInt(++index, offset);
				pst.setInt(++index, limit);
				rs = pst.executeQuery();
				listContact = new ArrayList<Contact>();
				// Lấy bản ghi
				while (rs.next()) {
					Contact contact = new Contact();
					contact.setContactId(rs.getInt("contact_id"));
					contact.setUserName(rs.getString("user_name"));
					contact.setEmail(rs.getString("email"));
					contact.setTel("tel");
					contact.setMessage(rs.getString("message"));
					contact.setDateContact(rs.getString("date"));
					listContact.add(contact);
				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return listContact;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.ContactDao#getTotalContact(java.lang.String)
	 */
	@Override
	public int getTotalContact(String keyName) throws SQLException, ClassNotFoundException {
		int total = 0;
		try {
			connectDB();
			if (con != null) {
				// Câu SQL
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT count(*) AS total ");
				sqlQuery.append("FROM tbl_contact ");
				if (keyName.trim().length() > 0) {
					sqlQuery.append("WHERE user_name LIKE ? ");
				}
				// Trường hợp có chọn group id
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				if (keyName.trim().length() > 0) {
					pst.setString(++index, "%" + keyName + "%");
				}
				rs = pst.executeQuery();
				// Lấy tổng số bản ghi
				while (rs.next()) {
					total = rs.getInt("total");
				}

			}
		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

		return total;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.ContactDao#deleteContact(int)
	 */
	@Override
	public void deleteContact(int contacId) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("DELETE FROM tbl_contact ");
			sqlQuery.append("WHERE contact_id = ? ");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setInt(++index, contacId);
			// Thực thi câu lệnh
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.ContactDao#checkExistContact(int)
	 */
	@Override
	public boolean checkExistContact(int contactId) throws SQLException, ClassNotFoundException {

		boolean check = false;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT contact_id ");
				sqlQuery.append("FROM tbl_contact ");
				sqlQuery.append("WHERE contact_id = ? ");

				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setInt(++index, contactId);
				rs = pst.executeQuery();
				// Lấy bản ghi
				if (rs.next()) {
					check = true;
				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

		return check;
	}

}
