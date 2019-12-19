package ytebnews.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ytebnews.entities.Category;
import ytebnews.logics.CategoryLogic;
import ytebnews.logics.impl.CategoryLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;

/**
 * Servlet implementation class ManageCategoryController
 */
@WebServlet(value = { Constant.INSERT_CATEGORY_URL, Constant.UPDATE_CATEGORY_URL })
public class ManageCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int categoryId = Common.parseInt(request.getParameter("categoryid"), 0);
			if (categoryId > 0) {
				CategoryLogic categoryLogic = new CategoryLogicImpl();
				request.setAttribute("category", categoryLogic.getCategoryById(categoryId));
				request.setAttribute("action", "update");

			}
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.EDIT_CATEGORY_JSP);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int categoryId = Common.parseInt(request.getParameter("categoryid"), 0);
			if (categoryId == 0) {
				CategoryLogic categoryLogic = new CategoryLogicImpl();
				Category category = new Category();
				String categoryName = request.getParameter("categoryname");
				category.setCategoryName(categoryName);
				categoryLogic.insertCategory(category);
				response.sendRedirect(request.getContextPath() + Constant.LIST_CATEGORY_URL + "?action=insertsuccess");
			} else if (categoryId > 0) {
				CategoryLogic categoryLogic = new CategoryLogicImpl();
				Category category = new Category();
				String categoryName = request.getParameter("categoryname");
				category.setCategoryName(categoryName);
				category.setCategoryId(categoryId);
				categoryLogic.updateCategory(category);
				response.sendRedirect(request.getContextPath() + Constant.LIST_CATEGORY_URL + "?action=updatesuccess");
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}
	}

}
