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
import ytebnews.logics.CategoryLogic;
import ytebnews.logics.impl.CategoryLogicImpl;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * Servlet implementation class ListCategoryController
 */
@WebServlet(value = { Constant.LIST_CATEGORY_URL })
public class ListCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter(Constant.ACTION);
			if (Constant.INSERT_SUCC.equals(action)) {
				request.setAttribute(Constant.INSERT_SUCC, MessageProperties.getMesage("MSG004"));
			} else if (Constant.UPDATE_SUCC.equals(action)) {
				request.setAttribute(Constant.UPDATE_SUCC, MessageProperties.getMesage("MSG005"));

			}
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			List<Category> listCategories = categoryLogic.getListCategory();
			request.setAttribute("listcategory", listCategories);
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.LIST_CATEGORY_JSP);
			dispatch.forward(request, response);
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}

	}

}
