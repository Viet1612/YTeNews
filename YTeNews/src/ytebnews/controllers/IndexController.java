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
import ytebnews.utils.Constant;

/**
 * Servlet implementation class IndexController
 */
@WebServlet(value = { Constant.INDEX_URL })
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			NewsLogic newsLogic = new NewsLogicImpl();
			List<News> listNewsByCategoryId = null;
			List<Category> listCategories = categoryLogic.getListCategory();
			List<News> listNewsHeader = newsLogic.getListNewsHome(5, 0, Constant.NOT_VIEW_ORDER);
			List<News> listNewsTrending = newsLogic.getListNewsHome(5, 0, Constant.VIEW_ORDER);
			for (int i = 0; i < listCategories.size(); i++) {
				listNewsByCategoryId = newsLogic.getListNewsHome(5, listCategories.get(i).getCategoryId(), Constant.NOT_VIEW_ORDER);
				request.setAttribute("category" + i, listNewsByCategoryId);
//				request.setAttribute(listCategories.get(i).getCategoryId(), listNewsByCategoryId);
			}
			request.setAttribute("listcategory", listCategories);
			request.setAttribute("listnewsheader", listNewsHeader);
			request.setAttribute("listnewstrending", listNewsTrending);
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.INDEX_JSP);
			dispatch.forward(request, response);
			
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}

		
	}


}
