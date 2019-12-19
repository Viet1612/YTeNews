/**
 * Copyright(C) 2019 Tran Thanh Viet
 * NewsLogic.java, Oct 15, 2019 VietTT
 */
package ytebnews.logics;

import java.sql.SQLException;
import java.util.List;

import ytebnews.entities.News;

/**
 * @author VietTT
 *
 */
public interface NewsLogic {
	/**
	 * Lấy số lượng bản ghi thỏa mãn điều kiện tìm kiếm
	 * 
	 * @param keyName    từ tìm kiếm seach bởi name
	 * @param categoryId id của category
	 * @return số lượng bản ghi
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int getTotalNews(String keyName, int categoryId, int approve, int userId) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy danh sách news
	 * 
	 * @param offset     offset vị trí data cần lấy nào
	 * @param limit      limit số lượng lấy
	 * @param categoryId mã category tìm kiếm
	 * @param keyName    Tên tìm kiếm
	 * @return danh sách user cần tìm
	 */
	public List<News> getListNews(int offset, int limit, int categoryId, String keyName, int approve, int userId)
			throws ClassNotFoundException, SQLException;

	/**
	 * Insert news khi người đăng add bài
	 */
	public void insertNewAuthor(News news) throws ClassNotFoundException, SQLException;
	
	/**
	 * Check tồn tại news id
	 * @param newsId
	 * @return true tồn tại
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean checkExistNewsId(int newsId) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy news băng id
	 * @param newsId
	 * @return news
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public News getNewsById(int newsId, int approve) throws ClassNotFoundException, SQLException;
	
	/**
	 * Phê duyeeth bài viết
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void approveNews(int newsId, int categoryId) throws ClassNotFoundException, SQLException;
	
	/**
	 * Xoá tin tức
	 * @param newsId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void deleteNews(int newsId) throws SQLException, ClassNotFoundException;
	
	/**
	 * Update tin tức 
	 * @param news
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void updateNews(News news) throws SQLException, ClassNotFoundException;

	/**
	 * Lấy 4 news hiển thih trên trang chủ
	 * @param limit số news muốn lấy
	 * @return List news
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<News> getListNewsHome(int limit, int categoryId, int view) throws ClassNotFoundException, SQLException;
	
	/**
	 * Lấy limit news hiển thih trên từng categories
	 * @param 
	 * @return List news
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<News> getListNewsCategories(int offset, int limit, int categoryId, String keyName, String orderByTrending ) throws ClassNotFoundException, SQLException;

	
	/**
	 * update view
	 * @param newsId
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void updateView(int newsId) throws SQLException, ClassNotFoundException;
}
