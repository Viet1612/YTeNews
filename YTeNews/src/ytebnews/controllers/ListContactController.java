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

import ytebnews.entities.Contact;
import ytebnews.logics.ContactLogic;
import ytebnews.logics.impl.ContactLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * Servlet implementation class ListContactController
 */
@WebServlet(value = { Constant.CONTACT_ADMIN_URL })
public class ListContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ContactLogic contactLogic = new ContactLogicImpl();
			HttpSession session = request.getSession();
			String keyName = Constant.KEYNAME_DEFAULT;
			int offset = Constant.OFFSET;
			int limit = Constant.LIMIT;
			int currentPage = Constant.CURRENT_PAGE_DEFAULT;
			String action = request.getParameter(Constant.ACTION);

			System.out.println(action);
			if (action != null) {
				if (Constant.SEARCH.equals(action) || Constant.PAGING.equals(action)) {
					keyName = request.getParameter(Constant.KEYNAME);
					System.out.println(keyName);
					if (Constant.PAGING.equals(action)) {
						// Lấy trang click
						currentPage = Common.parseInt(request.getParameter(Constant.CURRENT_PAGE),
								Constant.CURRENT_PAGE_DEFAULT);
					}
				} else if (Constant.DELE_SUCC.equals(action)) {
					request.setAttribute(Constant.DELE_SUCC, MessageProperties.getMesage("MSG003"));
				}

			}
			int totalContact = contactLogic.getTotalContact(keyName);
			if (totalContact > 0) {
				// Tổng số trang
				int totalPage = Common.getTotalPage(totalContact, limit);
				// Nếu currentPage > totalPage thì gắn currentPage = totalPage
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
				// Bản ghi đầu tiên lấy từ offset
				offset = Common.getOffset(currentPage, limit);
				// Xử lý hiển thị pagning
				List<Integer> listPaging = Common.getListPaging(totalContact, limit, currentPage);
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
				List<Contact> listContact = contactLogic.getListContact(offset, limit, keyName);
				request.setAttribute("listcontact", listContact);
			}

			session.setAttribute(Constant.KEYNAME, keyName);
			session.setAttribute(Constant.CURRENT_PAGE, currentPage);
			request.setAttribute("totalcontact", totalContact);

			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.CONTACT_ADMIN_JSP);
			dispatch.forward(request, response);

		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}
	}

}
