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
 * Servlet implementation class NewsSingleController
 */
@WebServlet(value =  Constant.NEWS_URL)
public class NewsSingleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			NewsLogic newsLogic = new NewsLogicImpl();
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			int newsId = Common.parseInt(request.getParameter(Constant.NEWS_ID), Constant.NEWS_ID_DEFAULT);
			// newsid paseInt không lỗi và tồn tại mới hiển thị 
			if (newsId > 0 && newsLogic.checkExistNewsId(newsId)) {
				News news = newsLogic.getNewsById(newsId, Constant.APPROVE_Y);
				List<Category> lisCategories = categoryLogic.getListCategory();
				newsLogic.updateView(newsId);
				// Set đối tượng lên rq
				List<News> listNewsTrending = newsLogic.getListNewsHome(4, 0, Constant.VIEW_ORDER);
				request.setAttribute("listnewstrending", listNewsTrending);
				request.setAttribute(Constant.NEWS, news);
				request.setAttribute("listcontent", Common.splitN(news.getContent()));
				request.setAttribute("listcategory", lisCategories);
				
				RequestDispatcher dispatch = request.getServletContext()
						.getRequestDispatcher(Constant.NEWS_SINGLE_JSP);
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


}
