/**
 * Copyright(C) 2019 Tran Thanh Viet
 * CategoryDaoImpl.java, Oct 13, 2019 VietTT
 */
package ytebnews.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ytebnews.dao.CategoryDao;
import ytebnews.entities.Category;
import ytebnews.utils.Constant;

/**
 * @author VietTT
 *
 */
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.CategoryDao#getListCategory()
	 */
	@Override
	public List<Category> getListCategory() throws ClassNotFoundException, SQLException {
		List<Category> liCategories = null;
		try {
			connectDB();
			if (con != null) {
				// Câu Query
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT category_name, category_id ");
				sqlQuery.append("FROM tbl_category ");
				sqlQuery.append("ORDER BY category_id ASC");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				rs = pst.executeQuery();
				// Lấy danh sách nhóm
				liCategories = new ArrayList<Category>();
				while (rs.next()) {
					Category category = new Category();
					category.setCategoryName(rs.getString(Constant.T_CATEGORY_NAME));
					category.setCategoryId(rs.getInt(Constant.T_CATEGORY_ID));
					liCategories.add(category);
				}
			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

		return liCategories;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.CategoryDao#insertCategory(ytebnews.entities.Category)
	 */
	@Override
	public void insertCategory(Category category) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("INSERT INTO tbl_category (category_name) ");
			sqlQuery.append("VALUE (?)");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setString(++index, category.getCategoryName());
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
	 * @see ytebnews.dao.CategoryDao#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(int categoryId) throws ClassNotFoundException, SQLException {
		Category category = null;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT * ");
				sqlQuery.append("FROM tbl_category ");
				sqlQuery.append("WHERE category_id = ?");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setLong(++index, categoryId);
				rs = pst.executeQuery();
				// Lấy bản ghi
				while (rs.next()) {
					category = new Category();
					category.setCategoryId(rs.getInt(Constant.T_CATEGORY_ID));
					category.setCategoryName(rs.getString(Constant.T_CATEGORY_NAME));

				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return category;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.CategoryDao#updateCategory(ytebnews.entities.Category)
	 */
	@Override
	public void updateCategory(Category category) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("UPDATE tbl_category ");
			sqlQuery.append("SET category_name = ? ");
			sqlQuery.append("WHERE category_id = ? ");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setString(++index, category.getCategoryName());
			pst.setInt(++index, category.getCategoryId());
			

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

}
