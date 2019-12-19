package ytebnews.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ytebnews.entities.User;
import ytebnews.logics.UserLogic;
import ytebnews.logics.impl.UserLogicImpl;
import ytebnews.utils.Constant;
import ytebnews.validates.ValidateUser;

/**
 * Servlet implementation class LoginAdminController
 */
@WebServlet(value = { Constant.LOGIN_URL })
public class LoginAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.LOGIN_ADMIN_JSP);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy loginname, pass từ req
			String loginName = request.getParameter(Constant.LOGIN_NAME);
			String pass = request.getParameter(Constant.PASS);
			HttpSession session = request.getSession();
			// Khai báo khởi tạo ValidateUser
			ValidateUser validate = new ValidateUser();
			UserLogic userLogic = new UserLogicImpl();
			// lấy danh sách lỗi
			String errMessage = validate.validateLoginAdmin(loginName, pass);
			
			
			System.out.println(loginName);
			if (!"".equals(errMessage)) {
				request.setAttribute(Constant.MESS_ERR, errMessage);
				request.setAttribute(Constant.LOGIN_NAME, loginName);
				// Hiển thị ADM001 với lỗi
				RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.LOGIN_ADMIN_JSP);
				dispatch.forward(request, response);
			} else {
				User user = userLogic.getUserByLoginName(loginName);
				session.setAttribute(Constant.LOGIN_NAME, loginName);
				session.setAttribute("rule", user.getRule());
				// Không có lỗi set loginname lên session
				if (user.getRule() == Constant.RULE_ADMIN) {
					// Chuyển đến màn index
					response.sendRedirect(request.getContextPath() + Constant.INDEX_ADMIN_URL);
				} else if (user.getRule() == Constant.RULE_AUTHOR) {
					session.setAttribute("userid", user.getUserId());
					session.setAttribute("rule", user.getRule());
					session.setAttribute("fullname", user.getFullName());
					response.sendRedirect(request.getContextPath() + Constant.LIST_NEWS_AUTHOR_URL);
				}
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);

		}
	}

}
