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
import ytebnews.entities.Contact;
import ytebnews.logics.CategoryLogic;
import ytebnews.logics.ContactLogic;
import ytebnews.logics.impl.CategoryLogicImpl;
import ytebnews.logics.impl.ContactLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;
import ytebnews.validates.Validate;

/**
 * Servlet implementation class ContactController
 */
@WebServlet(value = { Constant.CONTACT_URL })
public class ContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		CategoryLogic categoryLogic = new CategoryLogicImpl();
		List<Category> listCategories = categoryLogic.getListCategory();
		request.setAttribute("listcategory", listCategories);
		RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.CONTACT_JSP);
		dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// chyển đến mh lỗi
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
			Contact contact = getDefaultValue(request);
			Validate validate = new Validate();
			ContactLogic contactLogic = new ContactLogicImpl();
			CategoryLogic categoryLogic = new CategoryLogicImpl();
			List<Category> listCategories = categoryLogic.getListCategory();
			request.setAttribute("listcategory", listCategories);
			List<String> listErr = validate.validateContact(contact);
			if (listErr.size() > 0) {
				// Set list err lên req
				request.setAttribute("listerr", listErr);
				request.setAttribute("contact", contact);
				// Forward

			} else {
				request.setAttribute("msg006",  MessageProperties.getMesage(Constant.MSG007));
				contactLogic.insertContact(contact);
			}
			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.CONTACT_JSP);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// chyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);

		}

	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private Contact getDefaultValue(HttpServletRequest request) {
		Contact contact = new Contact();
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String message = request.getParameter("message");
		String date = Common.getTimeNow();
		contact.setUserName(userName);
		contact.setEmail(email);
		contact.setTel(tel);
		contact.setMessage(message);
		contact.setDateContact(date);
		return contact;
	}

}
