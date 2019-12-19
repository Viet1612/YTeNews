package ytebnews.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ytebnews.logics.NewsLogic;
import ytebnews.logics.impl.NewsLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;

/**
 * Servlet implementation class DeleteNewsController
 */
@WebServlet(value = { Constant.DELE_NEWS_ADMIN_URL, Constant.DELE_NEWS_AUTHOR_URL })
public class DeleteNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Khai báo khơi tạo đối tượng
			NewsLogic newsLogic = new NewsLogicImpl();
			String servletPath = request.getServletPath();
			// Lấy id từ rq
			int newsId = Common.parseInt(request.getParameter(Constant.NEWS_ID), Constant.NEWS_ID_DEFAULT);
			String path = Constant.LIST_NEWS_URL;
			if (newsId > 0 && newsLogic.checkExistNewsId(newsId)) {
				newsLogic.deleteNews(newsId);
				if (Constant.DELE_NEWS_ADMIN_URL.equals(servletPath)) {
					path = Constant.LIST_NEWS_URL;
				} else if (Constant.DELE_NEWS_AUTHOR_URL.equals(servletPath)) {
					path = Constant.LIST_NEWS_AUTHOR_URL;
				}

			} else {
				// chuyển đến mh lỗi
				path = Constant.SYSTEM_ERR_URL;
			}
			response.sendRedirect(request.getContextPath() + path + "?action=deletesuccess");
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}

	}

}
