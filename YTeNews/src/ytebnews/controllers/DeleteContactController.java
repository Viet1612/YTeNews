package ytebnews.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ytebnews.logics.ContactLogic;
import ytebnews.logics.impl.ContactLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;

/**
 * Servlet implementation class DeleteContactController
 */
@WebServlet(value = { Constant.DELETE_CONTACT_URL })
public class DeleteContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Khai báo khơi tạo đối tượng
			ContactLogic contactLogic = new ContactLogicImpl();
			// Lấy id từ rq
			int contacId = Common.parseInt(request.getParameter("contactid"), Constant.NEWS_ID_DEFAULT);
			String path = Constant.CONTACT_ADMIN_URL;
			if (contacId > 0 && contactLogic.checkExistContact(contacId)) {
				contactLogic.deleteContact(contacId);
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
