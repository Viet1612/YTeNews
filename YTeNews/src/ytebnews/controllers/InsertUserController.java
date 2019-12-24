package ytebnews.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ytebnews.entities.User;
import ytebnews.logics.UserLogic;
import ytebnews.logics.impl.UserLogicImpl;
import ytebnews.utils.Constant;
import ytebnews.validates.ValidateUser;

/**
 * Servlet implementation class InsertUserController
 */
@WebServlet(value = { Constant.INSERT_USER_URL })
public class InsertUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.INSERT_USER_JSP);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = getDefaultValue(request);
			UserLogic userLogic = new UserLogicImpl();
			ValidateUser validateUser = new ValidateUser();
			List<String> listErr = validateUser.validateUserInfor(user);
			if (listErr.size() > 0) {
				// Set list err lên req
				request.setAttribute("listerr", listErr);
				request.setAttribute("user", user);
				// Forward
				RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.INSERT_USER_JSP);
				dispatch.forward(request, response);
			} else {
				userLogic.insertUser(user);
				response.sendRedirect(request.getContextPath() + Constant.LIST_USER_URL + "?action=insertsuccess");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// chyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);

		}
	}
	
	private User getDefaultValue(HttpServletRequest request) {
		User user = new User();
		user.setLoginName(request.getParameter("username"));
		System.out.println(user.getLoginName());
		user.setFullName(request.getParameter("fullname"));
		user.setEmail(request.getParameter("email"));
		user.setTel(request.getParameter("tel"));
		user.setPass(request.getParameter("password"));
		user.setPassConf(request.getParameter("repassoword"));
		user.setRule(Constant.RULE_AUTHOR);
		return user;
	}

}
