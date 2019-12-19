/**
 * Copyright(C) 2019 Tran Thanh Viet
 * NewsDaoImpl.java, Oct 15, 2019 VietTT
 */
package ytebnews.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ytebnews.dao.NewsDao;
import ytebnews.entities.News;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;

/**
 * @author VietTT
 *
 */
public class NewsDaoImpl extends BaseDaoImpl implements NewsDao {

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.NewsDao#getTotalNews(java.lang.String, int)
	 */
	@Override
	public int getTotalNews(String keyName, int categoryId, int approve, int userId)
			throws ClassNotFoundException, SQLException {
		int total = 0;
		try {
			connectDB();
			if (con != null) {
				int index = 0;
				boolean checkGrEmpty = false;
				boolean checkNameEmpty = false;
				// Câu SQL
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT count(*) AS total ");
				sqlQuery.append("FROM (tbl_news AS n ");
				sqlQuery.append("LEFT JOIN tbl_category AS c ");
				sqlQuery.append("ON n.category_id = c.category_id) ");
				sqlQuery.append("INNER JOIN tbl_user AS u ");
				sqlQuery.append("ON u.user_id = n.user_id ");
				sqlQuery.append("WHERE 1=1 ");
				// Trường hợp có chọn group id
				if (approve >= 0) {
					sqlQuery.append("AND n.approve = ? ");
				}
				if (userId > 0) {
					sqlQuery.append("AND u.user_id = ? ");
				}
				if (categoryId > 0) {
					sqlQuery.append("AND c.category_id = ? ");
					checkGrEmpty = true;
				}
				// Trường hợp có nhập full name
				if (keyName.length() > 0) {
					sqlQuery.append("AND n.news_name LIKE ? ");
					checkNameEmpty = true;
				}
				/// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				/// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				if (approve >= 0) {
					pst.setInt(++index, approve);
				}
				if (userId > 0) {
					pst.setInt(++index, userId);
				}
				if (checkGrEmpty) {
					pst.setInt(++index, categoryId);
				}
				if (checkNameEmpty) {

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
	 * @see ytebnews.dao.NewsDao#getListNews(int, int, int, java.lang.String)
	 */
	@Override
	public List<News> getListNews(int offset, int limit, int categoryId, String keyName, int approve, int userId)
			throws ClassNotFoundException, SQLException {

		List<News> listNews = null;
		try {
			connectDB();
			if (con != null) {
				int index = 0;
				// Câu SQL
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append(
						"SELECT n.news_id, n.category_id, n.news_name, n.approve, c.category_id, c.category_name, n.description, n.content, n.image, n.date, n.user_id, u.full_name, n.view ");
				sqlQuery.append("FROM (tbl_news AS n LEFT JOIN tbl_category AS c ");
				sqlQuery.append("ON n.category_id = c.category_id) ");
				sqlQuery.append("INNER JOIN tbl_user AS u ");
				sqlQuery.append("ON u.user_id = n.user_id ");
				sqlQuery.append("WHERE 1=1  ");
				if (approve >= 0) {
					sqlQuery.append("AND n.approve = ? ");
				}
				if (userId > 0) {
					sqlQuery.append("AND u.user_id = ? ");
				}
				// Trường hợp có chọn group id
				if (categoryId > 0) {
					sqlQuery.append("AND c.category_id = ? ");
				}
				// Trường hợp có nhập full name
				if (keyName.trim().length() > 0) {
					sqlQuery.append("AND n.news_name LIKE ? ");
				}
				sqlQuery.append("ORDER BY n.date DESC ");
				sqlQuery.append(" LIMIT ?, ?;");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				if (approve >= 0) {
					pst.setInt(++index, approve);
				}
				if (userId > 0) {
					pst.setInt(++index, userId);
				}
				if (categoryId > 0) {
					pst.setInt(++index, categoryId);
				}
				if (keyName.trim().length() > 0) {
					pst.setString(++index, "%" + keyName + "%");
				}
				pst.setInt(++index, offset);
				pst.setInt(++index, limit);
				rs = pst.executeQuery();
				// Lấy các bản ghi
				listNews = new ArrayList<News>();
				while (rs.next()) {
					News news = new News();
					// Lấy thông tin từng user
					news.setNewsId(rs.getInt(Constant.T_NEWS_ID));
					news.setNewsName(rs.getString(Constant.T_NEWS_NAME));
					news.setDescription(rs.getString(Constant.T_DESCRIPTION));
					news.setContent(rs.getString(Constant.T_CONTENT));
					news.setImage(rs.getString(Constant.T_IMAGE));
					news.setDatePost(rs.getString(Constant.T_DATE));
					news.setUserName(rs.getString(Constant.T_FUll_NAME));
					news.setView(rs.getLong(Constant.T_VIEW));
					news.setCategoryId(rs.getInt(Constant.T_CATEGORY_ID));
					news.setApprove(rs.getInt("approve"));
					news.setCategoryName(rs.getString(Constant.T_CATEGORY_NAME));
					listNews.add(news);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		}

		return listNews;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.NewsDao#insertNewAuthor()
	 */
	@Override
	public void insertNewAuthor(News news) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"INSERT INTO tbl_news (user_id, news_name, description, content, image, date, view, approve)");
			sqlQuery.append("VALUE (?, ?, ?, ?, ?, ?, ?, ?)");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			// Lấy lại key userId tự tăng khi insert
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setInt(++index, news.getUserId());
			pst.setString(++index, news.getNewsName());
			pst.setString(++index, news.getDescription());
			pst.setString(++index, news.getContent());
			pst.setString(++index, news.getImage());
			pst.setString(++index, news.getDatePost());
			pst.setLong(++index, news.getView());
			pst.setInt(++index, news.getApprove());
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
	 * @see ytebnews.dao.NewsDao#checkExistNewsId(int)
	 */
	@Override
	public boolean checkExistNewsId(int newsId) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append("SELECT news_id ");
				sqlQuery.append("FROM tbl_news ");
				sqlQuery.append("WHERE news_id = ? ");

				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setInt(++index, newsId);
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

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.NewsDao#getNewsById(int)
	 */
	@Override
	public News getNewsById(int newsId, int approve) throws ClassNotFoundException, SQLException {
		News news = null;
		try {
			connectDB();
			if (con != null) {
				// Câu SQl
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append(
						"SELECT n.news_id, n.category_id, n.news_name, n.approve, c.category_id, c.category_name, n.description, n.content, n.image, n.date, n.user_id, u.full_name, n.view ");
				sqlQuery.append("FROM (tbl_news AS n LEFT JOIN tbl_category AS c ");
				sqlQuery.append("ON n.category_id = c.category_id) ");
				sqlQuery.append("INNER JOIN tbl_user AS u ");
				sqlQuery.append("ON u.user_id = n.user_id ");
				sqlQuery.append("WHERE n.news_id = ? ");
				if (approve == Constant.APPROVE_Y) {
					sqlQuery.append("AND n.approve = ? ");
				}
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());
				int index = 0;
				pst.setLong(++index, newsId);
				if (approve == Constant.APPROVE_Y) {
					pst.setInt(++index, Constant.APPROVE_Y);
				}

				rs = pst.executeQuery();
				// Lấy bản ghi
				while (rs.next()) {
					news = new News();
					news.setNewsId(rs.getInt(Constant.T_NEWS_ID));
					news.setNewsName(rs.getString(Constant.T_NEWS_NAME));
					news.setDescription(rs.getString(Constant.T_DESCRIPTION));
					news.setContent(rs.getString(Constant.T_CONTENT));
					news.setImage(rs.getString(Constant.T_IMAGE));
					news.setDatePost(rs.getString(Constant.T_DATE));
					news.setUserName(rs.getString(Constant.T_FUll_NAME));
					news.setView(rs.getLong(Constant.T_VIEW));
					news.setCategoryId(rs.getInt(Constant.T_CATEGORY_ID));
					news.setApprove(rs.getInt("approve"));
					news.setCategoryName(rs.getString(Constant.T_CATEGORY_NAME));
				}

			}

		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}
		return news;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.NewsDao#approveNews()
	 */
	@Override
	public void approveNews(int newsId, int categoryId) throws ClassNotFoundException, SQLException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("UPDATE tbl_news ");
			sqlQuery.append("SET approve = ?, date = ?, category_id = ? ");
			sqlQuery.append("WHERE news_id = ? ");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setInt(++index, Constant.APPROVE_Y);
			pst.setString(++index, Common.getTimeNow());
			pst.setInt(++index, categoryId);
			pst.setInt(++index, newsId);
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
	 * @see ytebnews.dao.NewsDao#deleteNews(int)
	 */
	@Override
	public void deleteNews(int newsId) throws SQLException, ClassNotFoundException {
		try {
			connectDB();
			// TẠo câu sql
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("DELETE FROM tbl_news ");
			sqlQuery.append("WHERE news_id = ? ");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setInt(++index, newsId);
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
	 * @see ytebnews.dao.NewsDao#updateNews(News)
	 */
	@Override
	public void updateNews(News news) throws SQLException, ClassNotFoundException {
		try {
			connectDB();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("UPDATE tbl_news ");
			sqlQuery.append("SET news_name = ?, description = ?, content = ?, date = ? ");
			if (news.getImage() != null) {
				sqlQuery.append(", image = ?");
			}
			sqlQuery.append("WHERE news_id = ? ");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			// Truyền các giá trị value
			pst.setString(++index, news.getNewsName());
			pst.setString(++index, news.getDescription());
			pst.setString(++index, news.getContent());
			pst.setString(++index, news.getDatePost());
			if (news.getImage() != null) {
				pst.setString(++index, news.getImage());
			}
			pst.setLong(++index, news.getNewsId());

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
	 * @see ytebnews.dao.NewsDao#getListNewsLatest()
	 */
	@Override
	public List<News> getListNewsHome(int limit, int categoryId, int view) throws ClassNotFoundException, SQLException {
		List<News> listNews = null;
		try {
			connectDB();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"SELECT n.news_id, n.category_id, n.news_name, n.approve, c.category_id, c.category_name, n.description, n.content, n.image, n.date, n.user_id, u.full_name, n.view ");
			sqlQuery.append("FROM (tbl_news AS n LEFT JOIN tbl_category AS c ");
			sqlQuery.append("ON n.category_id = c.category_id) ");
			sqlQuery.append("INNER JOIN tbl_user AS u ");
			sqlQuery.append("ON u.user_id = n.user_id ");
			sqlQuery.append("WHERE n.approve = ? ");
			if (categoryId > 0) {
				sqlQuery.append("AND n.category_id = ? ");
			}
			if (view == Constant.NOT_VIEW_ORDER) {
				sqlQuery.append("ORDER BY n.date DESC ");
			} else if (view == Constant.VIEW_ORDER) {
				sqlQuery.append("ORDER BY n.view DESC ");
			}
			if (limit > 0) {
				sqlQuery.append("LIMIT 0, ?;");
			}
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			pst.setInt(++index, Constant.APPROVE_Y);
			if (categoryId > 0) {
				pst.setInt(++index, categoryId);
			}
			if (limit > 0) {
				pst.setInt(++index, limit);
			}
			rs = pst.executeQuery();
			// Lấy các bản ghi
			listNews = new ArrayList<News>();
			while (rs.next()) {
				News news = new News();
				// Lấy thông tin từng user
				news.setNewsId(rs.getInt(Constant.T_NEWS_ID));
				news.setNewsName(rs.getString(Constant.T_NEWS_NAME));
				news.setDescription(rs.getString(Constant.T_DESCRIPTION));
				news.setContent(rs.getString(Constant.T_CONTENT));
				news.setImage(rs.getString(Constant.T_IMAGE));
				news.setDatePost(rs.getString(Constant.T_DATE));
				news.setUserName(rs.getString(Constant.T_FUll_NAME));
				news.setView(rs.getLong(Constant.T_VIEW));
				news.setCategoryId(rs.getInt(Constant.T_CATEGORY_ID));
				news.setApprove(rs.getInt("approve"));
				news.setCategoryName(rs.getString(Constant.T_CATEGORY_NAME));
				listNews.add(news);
			}
		} catch (SQLException e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		} finally {
			closeConnectDB();
		}

		return listNews;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.NewsDao#getListNewsCategories(int, int, int,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<News> getListNewsCategories(int offset, int limit, int categoryId, String keyName,
			String orderByTrending) throws ClassNotFoundException, SQLException {

		List<News> listNews = null;
		try {
			connectDB();
			if (con != null) {
				int index = 0;
				// Câu SQL
				StringBuilder sqlQuery = new StringBuilder();
				sqlQuery.append(
						"SELECT n.news_id, n.category_id, n.news_name, n.approve, c.category_id, c.category_name, n.description, n.content, n.image, n.date, n.user_id, u.full_name, n.view ");
				sqlQuery.append("FROM (tbl_news AS n LEFT JOIN tbl_category AS c ");
				sqlQuery.append("ON n.category_id = c.category_id) ");
				sqlQuery.append("INNER JOIN tbl_user AS u ");
				sqlQuery.append("ON u.user_id = n.user_id ");
				sqlQuery.append("WHERE approve = 1  ");
				// Trường hợp có chọn group id
				if (categoryId > 0) {
					sqlQuery.append("AND c.category_id = ? ");
				}
				// Trường hợp có nhập full name
				if (keyName.trim().length() > 0) {
					sqlQuery.append("AND n.news_name LIKE ? ");
				}
				if (orderByTrending.trim().length() > 0) {
					sqlQuery.append("ORDER BY n.view DESC ");
				} else {
					sqlQuery.append("ORDER BY n.date DESC ");
				}
				sqlQuery.append(" LIMIT ?, ?;");
				// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
				// csdl
				pst = con.prepareStatement(sqlQuery.toString());

				if (categoryId > 0) {
					pst.setInt(++index, categoryId);
				}
				if (keyName.trim().length() > 0) {
					pst.setString(++index, "%" + keyName + "%");
				}
				pst.setInt(++index, offset);
				pst.setInt(++index, limit);
				rs = pst.executeQuery();
				// Lấy các bản ghi
				listNews = new ArrayList<News>();
				while (rs.next()) {
					News news = new News();
					// Lấy thông tin từng user
					news.setNewsId(rs.getInt(Constant.T_NEWS_ID));
					news.setNewsName(rs.getString(Constant.T_NEWS_NAME));
					news.setDescription(rs.getString(Constant.T_DESCRIPTION));
					news.setContent(rs.getString(Constant.T_CONTENT));
					news.setImage(rs.getString(Constant.T_IMAGE));
					news.setDatePost(rs.getString(Constant.T_DATE));
					news.setUserName(rs.getString(Constant.T_FUll_NAME));
					news.setView(rs.getLong(Constant.T_VIEW));
					news.setCategoryId(rs.getInt(Constant.T_CATEGORY_ID));
					news.setApprove(rs.getInt("approve"));
					news.setCategoryName(rs.getString(Constant.T_CATEGORY_NAME));
					listNews.add(news);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			throw e;
		}

		return listNews;
	}

	/*
	 * (non-javadoc)
	 * 
	 * @see ytebnews.dao.NewsDao#updateView(int)
	 */
	@Override
	public void updateView(int newsId) throws SQLException, ClassNotFoundException {
		try {
			connectDB();
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("UPDATE tbl_news ");
			sqlQuery.append("SET view = view + 1 ");
			sqlQuery.append("WHERE news_id = ? ");
			// Tao đối tượng prepareStatement để gửi các câu lệnh sql được tham số hóa đến
			// csdl
			pst = con.prepareStatement(sqlQuery.toString());
			int index = 0;
			pst.setLong(++index, newsId);
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
