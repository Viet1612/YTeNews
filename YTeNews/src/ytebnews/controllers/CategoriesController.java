package ytebnews.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ytebnews.entities.Category;
import ytebnews.entities.News;
import ytebnews.logics.CategoryLogic;
import ytebnews.logics.NewsLogic;
import ytebnews.logics.impl.CategoryLogicImpl;
import ytebnews.logics.impl.NewsLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;

/**
 * Servlet implementation class CategoriesController
 */
@WebServlet(value =  Constant.CATERGORIES_NEWS_URL)
public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			NewsLogic newsLogic = new NewsLogicImpl();
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			List<Category> listCategories = categoryLogic.getListCategory();
			String keyName = Constant.KEYNAME_DEFAULT;
			int categoryId = Constant.CATEGORY_ID_DEFAULT;
			int offset = Constant.OFFSET;
			int limit = Constant.LIMIT;
			int currentPage = Constant.CURRENT_PAGE_DEFAULT;
			String orderByTrending = Constant.KEYNAME_DEFAULT;
			String action = request.getParameter(Constant.ACTION);
			String categoryName = Constant.KEYNAME_DEFAULT;

			System.out.println(action);

			if (Constant.RECENT.equals(action) || Constant.TRENDING.equals(action)) {
				if (Constant.TRENDING.equals(action)) {
					orderByTrending = Constant.TRENDING;
					categoryName = Constant.TRENDING_CATE;
				} else {
					categoryName = Constant.RECENT_NEWS;
				}

			} else if (action == null) {
				categoryId = Common.parseInt(request.getParameter(Constant.CATEGORY_ID), Constant.CATEGORY_ID_DEFAULT);
			} else if (Constant.SEARCH.equals(action) || Constant.PAGING.equals(action)) {
				keyName = request.getParameter(Constant.KEYNAME);
				categoryId = Common.parseInt(request.getParameter(Constant.CATEGORY_ID), Constant.CATEGORY_ID_DEFAULT);
				categoryName = Constant.SEARCH_LIST;
				if (Constant.PAGING.equals(action)) {
					// Lấy trang click
					currentPage = Common.parseInt(request.getParameter(Constant.CURRENT_PAGE),
							Constant.CURRENT_PAGE_DEFAULT);
					orderByTrending = request.getParameter(Constant.TRENDING);
				}
			}
			int totalNews = newsLogic.getTotalNews(keyName, categoryId, Constant.APPROVE_Y, Constant.USER_ID_DEFAULT);
			if (totalNews > 0) {
				// Tổng số trang
				int totalPage = Common.getTotalPage(totalNews, limit);
				// Nếu currentPage > totalPage thì gắn currentPage = totalPage
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
				// Bản ghi đầu tiên lấy từ offset
				offset = Common.getOffset(currentPage, limit);
				// Xử lý hiển thị pagning
				List<Integer> listPaging = Common.getListPaging(totalNews, limit, currentPage);
				if (listPaging.size() > 0) {
					// Xử lý link >>
					if (totalPage > listPaging.get(listPaging.size() - 1)) {
						request.setAttribute(Constant.NEXT, listPaging.get(listPaging.size() - 1) + 1);
						request.setAttribute(Constant.NEXT_CHAR_AT, Constant.NEXT_CHAR);
					}
					// Xử lý link <<
					if (listPaging.get(listPaging.size() - 1) > Constant.PAGING_PAGE) {
						request.setAttribute(Constant.PREVIOUS, listPaging.get(0) - Constant.PAGING_PAGE);
						request.setAttribute(Constant.PREVIOUS_CHAR_AT, Constant.PRE_CHAR);
					}
					request.setAttribute("listPaging", listPaging);
				}
				// LẤY DANH sách News
				List<News> listNews = newsLogic.getListNewsCategories(offset, limit, categoryId, keyName, orderByTrending);
				request.setAttribute("listNews", listNews);
			}
			if(categoryId > 0) {
				Category category = categoryLogic.getCategoryById(categoryId);
				categoryName = category.getCategoryName();
			}
			List<News> listNewsTrending = newsLogic.getListNewsHome(4, 0, Constant.VIEW_ORDER);
			request.setAttribute("listnewstrending", listNewsTrending);
			request.setAttribute("categoryname", categoryName);
			request.setAttribute(Constant.KEYNAME, keyName);
			request.setAttribute(Constant.CATEGORY_ID, categoryId);
			request.setAttribute(Constant.CURRENT_PAGE, currentPage);
			request.setAttribute(Constant.TRENDING, orderByTrending);
			request.setAttribute("listcategory", listCategories);
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.CATEGORIES_JSP);
			dispatch.forward(request, response);

		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}
	}

}
