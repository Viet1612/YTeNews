package ytebnews.dao.impl;

import java.sql.SQLException;

import ytebnews.dao.ContactDao;
import ytebnews.entities.Contact;

public class ContactDaoImpl extends BaseDaoImpl implements ContactDao{

	/* (non-javadoc)
	 * @see ytebnews.dao.ContactDao#insertContact(ytebnews.entities.Contact)
	 */
	@Override
	public void insertContact(Contact contact) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"INSERT INTO tbl_contact (user_name, email, tel, message, date)");
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

}
