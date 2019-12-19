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

/**
 * Servlet implementation class DetailNewAdminController
 */
@WebServlet(value = { Constant.VIEW_NEWS_URL, Constant.APPROVE_URL, Constant.VIEW_NEWS_AU_URL })
public class DetailNewAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Khai báo khơi tạo đối tượng
			NewsLogic newsLogic = new NewsLogicImpl();
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			HttpSession session = request.getSession();
			int rule = (int) session.getAttribute("rule");
			// Lấy id từ rq
			int newsId = Common.parseInt(request.getParameter(Constant.NEWS_ID), Constant.NEWS_ID_DEFAULT);
			// newsid paseInt không lỗi và tồn tại mới hiển thị 
			if (newsId > 0 && newsLogic.checkExistNewsId(newsId)) {
				News news = newsLogic.getNewsById(newsId, Constant.APPROVE_DC);
				List<Category> lisCategories = categoryLogic.getListCategory();
				// Set đối tượng lên rq
				request.setAttribute(Constant.NEWS, news);
				request.setAttribute("listcategory", lisCategories);
				request.setAttribute("rule", rule);
				RequestDispatcher dispatch = request.getServletContext()
						.getRequestDispatcher(Constant.DETAIL_NEWS_ADMIN_JSP);
				dispatch.forward(request, response);
			} else {
				// chuyển đến mh lỗi
				response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);

		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Khai báo khơi tạo đối tượng
			NewsLogic newsLogic = new NewsLogicImpl();
			// Lấy id từ rq
			int newsId = Common.parseInt(request.getParameter(Constant.NEWS_ID), Constant.NEWS_ID_DEFAULT);
			int catelogyId = Common.parseInt(request.getParameter(Constant.CATEGORY_ID), Constant.CATEGORY_ID_DEFAULT);
			System.out.println(catelogyId);
			// userId paseInt không lỗi và tồn tại mới cho phê duyệt
			if (newsId > 0 && newsLogic.checkExistNewsId(newsId)) {
				newsLogic.approveNews(newsId, catelogyId);
				response.sendRedirect(request.getContextPath() + Constant.LIST_NEWS_URL + "?action=approvesuccess");
			} else {
				// chuyển đến mh lỗi
				response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);

		}
	 }

}
