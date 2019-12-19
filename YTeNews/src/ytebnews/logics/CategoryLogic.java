/**
 * Copyright(C) 2019 Tran Thanh Viet
 * CategoryLogic.java, Oct 13, 2019 VietTT
 */
package ytebnews.logics;

import java.sql.SQLException;
import java.util.List;

import ytebnews.entities.Category;

/**
 * @author VietTT
 *
 */
public interface CategoryLogic {
	
	/**
	 * Lấy danh sách Category từ DB
	 * 
	 * @return List Category
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Category> getListCategory() throws ClassNotFoundException, SQLException;
	
	/**
	 * Thêm chủ đề
	 * @param category
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void insertCategory(Category category) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy chủ để bởi id
	 * @param categoryId
	 * @return category
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Category getCategoryById(int categoryId) throws ClassNotFoundException, SQLException;
	
	/**
	 * update chủ đề
	 * @param category
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void updateCategory(Category category) throws ClassNotFoundException, SQLException;

}
