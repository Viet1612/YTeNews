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

import ytebnews.entities.User;
import ytebnews.logics.UserLogic;
import ytebnews.logics.impl.UserLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * Servlet implementation class ListUserController
 */
@WebServlet(value = { Constant.LIST_USER_URL})
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserLogic userLogic = new UserLogicImpl();
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
				} else if (Constant.BACK.equals(action)) {
					// lấy từ session
					keyName = (String) session.getAttribute(Constant.KEYNAME);
					currentPage = (int) session.getAttribute(Constant.CURRENT_PAGE);
				} else if (Constant.INSERT_SUCC.equals(action)) {
					request.setAttribute(Constant.INSERT_SUCC, MessageProperties.getMesage("MSG001"));
				} else if (Constant.DELE_SUCC.equals(action)) {
					request.setAttribute(Constant.DELE_SUCC, MessageProperties.getMesage("MSG003"));
				} else if (Constant.UPDATE_SUCC.equals(action)) {
					request.setAttribute(Constant.UPDATE_SUCC, MessageProperties.getMesage("MSG006"));

				}

			}
			int totalUser = userLogic.getTotalUser(keyName);
			if (totalUser > 0) {
				// Tổng số trang
				int totalPage = Common.getTotalPage(totalUser, limit);
				// Nếu currentPage > totalPage thì gắn currentPage = totalPage
				if (currentPage > totalPage) {
					currentPage = totalPage;
				}
				// Bản ghi đầu tiên lấy từ offset
				offset = Common.getOffset(currentPage, limit);
				// Xử lý hiển thị pagning
				List<Integer> listPaging = Common.getListPaging(totalUser, limit, currentPage);
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
				List<User> listUser = userLogic.getListUser(offset, limit, keyName);
				request.setAttribute("listuser", listUser);
			}
			
			session.setAttribute(Constant.KEYNAME, keyName);
			session.setAttribute(Constant.CURRENT_PAGE, currentPage);
			request.setAttribute(Constant.TOTAL_USER, totalUser);

			RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.MANAGER_USER_JSP);
			dispatch.forward(request, response);

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
