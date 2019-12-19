package ytebnews.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ytebnews.entities.Category;
import ytebnews.entities.News;
import ytebnews.logics.CategoryLogic;
import ytebnews.logics.NewsLogic;
import ytebnews.logics.impl.CategoryLogicImpl;
import ytebnews.logics.impl.NewsLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * Servlet implementation class ListNewsController
 */
@WebServlet(value = { Constant.LIST_NEWS_URL, Constant.LIST_NEWS_AUTHOR_URL })
public class ListNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			NewsLogic newsLogic = new NewsLogicImpl();
			HttpSession session = request.getSession();
			String servletPath = request.getServletPath();
			String path = "";
			int userId = Constant.USER_ID_DEFAULT;
			int approve = Constant.APPROVE_DC;

			if (Constant.LIST_NEWS_AUTHOR_URL.equals(servletPath)) {
				userId = (int) session.getAttribute("userid");
			}
			String keyName = Constant.KEYNAME_DEFAULT;
			int categoryId = Constant.CATEGORY_ID_DEFAULT;
			int offset = Constant.OFFSET;
			int limit = Constant.LIMIT;
			int currentPage = Constant.CURRENT_PAGE_DEFAULT;

			String action = request.getParameter(Constant.ACTION);

			System.out.println(action);

			if (action != null) {
				if (Constant.SEARCH.equals(action) || Constant.PAGING.equals(action)) {
					keyName = request.getParameter(Constant.KEYNAME);
					categoryId = Common.parseInt(request.getParameter(Constant.CATEGORY_ID),
							Constant.CATEGORY_ID_DEFAULT);
					System.out.println(keyName);
					approve = Common.parseInt(request.getParameter(Constant.APPROVE), Constant.APPROVE_DEFAULT);
					if (Constant.PAGING.equals(action)) {
						// Lấy trang click
						currentPage = Common.parseInt(request.getParameter(Constant.CURRENT_PAGE),
								Constant.CURRENT_PAGE_DEFAULT);
					}
				} else if (Constant.BACK.equals(action)) {
					// lấy từ session
					approve = (int) session.getAttribute(Constant.APPROVE);
					keyName = (String) session.getAttribute(Constant.KEYNAME);
					categoryId = (int) session.getAttribute(Constant.CATEGORY_ID);
					currentPage = (int) session.getAttribute(Constant.CURRENT_PAGE);
				} else if (Constant.INSERT_SUCC.equals(action)) {
					request.setAttribute(Constant.INSERT_SUCC, MessageProperties.getMesage("MSG001"));
				} else if (Constant.APPROVE_SUCC.equals(action) && Constant.LIST_NEWS_URL.equals(servletPath)) {
					request.setAttribute(Constant.APPROVE_SUCC, MessageProperties.getMesage("MSG002"));
				} else if (Constant.DELE_SUCC.equals(action)) {
					request.setAttribute(Constant.DELE_SUCC, MessageProperties.getMesage("MSG003"));
				} else if (Constant.UPDATE_SUCC.equals(action)) {
					request.setAttribute(Constant.UPDATE_SUCC, MessageProperties.getMesage("MSG006"));

				}

			}
			int totalNews = newsLogic.getTotalNews(keyName, categoryId, approve, userId);
			List<Category> lisCategories = categoryLogic.getListCategory();
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
				List<News> listNews = newsLogic.getListNews(offset, limit, categoryId, keyName, approve, userId);
				request.setAttribute("listNews", listNews);
			}
			request.setAttribute("listcategory", lisCategories);
			session.setAttribute(Constant.APPROVE, approve);
			session.setAttribute(Constant.KEYNAME, keyName);
			session.setAttribute(Constant.CATEGORY_ID, categoryId);
			session.setAttribute(Constant.CURRENT_PAGE, currentPage);
			request.setAttribute(Constant.TOTAL_NEWS, totalNews);

			// forward đến trang quản lý news
			if (Constant.LIST_NEWS_AUTHOR_URL.equals(servletPath)) {
				path = Constant.MANAGER_NEWS_AUTHOR_JSP;
			} else if (Constant.LIST_NEWS_URL.equals(servletPath)) {
				path = Constant.MANAGER_NEWS_JSP;
			}
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(path);
			dispatch.forward(request, response);

		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}
	}

}
