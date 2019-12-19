package ytebnews.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ytebnews.entities.News;
import ytebnews.logics.NewsLogic;
import ytebnews.logics.UserLogic;
import ytebnews.logics.impl.NewsLogicImpl;
import ytebnews.logics.impl.UserLogicImpl;
import ytebnews.utils.Common;
import ytebnews.utils.Constant;
import ytebnews.utils.MessageProperties;

/**
 * Servlet implementation class EditNewsController
 */
@WebServlet(value = { Constant.UPDATE_NEWS_URL })
public class EditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			NewsLogic newsLogic = new NewsLogicImpl();
			int newsId = Common.parseInt(request.getParameter(Constant.NEWS_ID), Constant.NEWS_ID_DEFAULT);
			// userId paseInt không lỗi và tồn tại mới hiển thị
			if (newsId > 0 && newsLogic.checkExistNewsId(newsId)) {
				// Lấy news bằng userId
				News news = newsLogic.getNewsById(newsId, Constant.APPROVE_DC);
				// Set đối tượng lên rq
				request.setAttribute(Constant.NEWS, news);
				RequestDispatcher dispatch = request.getServletContext().getRequestDispatcher(Constant.EDIT_NEWS_JSP);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			UserLogic userLogic = new UserLogicImpl();
			NewsLogic newsLogic = new NewsLogicImpl();
			int userId = (int) session.getAttribute(Constant.USER_ID);
			File file = null;
			FileItem fileImage = null;
			if (userLogic.checkUserIdAuthor(userId)) {
				News news = new News();
				String image = Common.getSalt();
				String nameImage = "";
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setSizeThreshold(Constant.MEMORY_THRESHOLD);
				fileItemFactory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
				// sets maximum size of upload file
				upload.setFileSizeMax(Constant.MAX_FILE_SIZE);
				// sets maximum size of request (include file + form data)
				upload.setSizeMax(Constant.MAX_REQUEST_SIZE);
				List<FileItem> fileItems = upload.parseRequest(request);
				int i = 0;
				String title = "";
				String description = "";
				String content = "";
				String newsId = "";
				for (FileItem fileItem : fileItems) {
					i = i + 1;
					// Lấy title
					if (i == 1) {
						title = new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8");
					} else if (i == 2) {
						description = new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8"); // des
					} else if (!fileItem.isFormField() && i == 3) {
						nameImage = fileItem.getName();
						// xử lý file upload ảnh
						if (!"".equals(image)) {
							String dirUrl = request.getServletContext().getRealPath("") + File.separator
									+ Constant.IMAGES_NEWS_FOLDER;
							File dir = new File(dirUrl);
							if (!dir.exists()) {
								dir.mkdir();
							}
							String fileImg = dirUrl + File.separator + image + "-" + nameImage;
							file = new File(fileImg);
							fileImage = fileItem;
						}
					} else if (i == 4) {
						content = new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8");// content
					} else if (i == 5) {
						newsId = new String(fileItem.getString().getBytes("iso-8859-1"), "UTF-8");
					}
				}
				news.setNewsName(title);
				news.setDescription(description);
				if (fileImage.getSize() > 0) {
					news.setImage(image + "-" + nameImage);
				}
				news.setContent(content);
				news.setDatePost(Common.getTimeNow());
				news.setNewsId(Integer.parseInt(newsId));
				// Check nhập
				if (!(Common.checkEmpty(title) && Common.checkEmpty(description) && Common.checkEmpty(content))) {
					request.setAttribute("err", MessageProperties.getMesage(Constant.ER001));
					request.setAttribute(Constant.NEWS, news);
					RequestDispatcher dispatch = request.getServletContext()
							.getRequestDispatcher(Constant.EDIT_NEWS_JSP);
					dispatch.forward(request, response);
				} else if (!Common.checkLength(Constant.MIN_LENTH_TITLE_DES, Constant.MAX_LENTH_TITLE_DES, title)) {
					request.setAttribute("err", MessageProperties.getMesage(Constant.ER002));
					request.setAttribute(Constant.NEWS, news);
					RequestDispatcher dispatch = request.getServletContext()
							.getRequestDispatcher(Constant.INSERT_NEWS_JSP);
					dispatch.forward(request, response);
				} else if (!Common.checkLength(Constant.MIN_LENTH_TITLE_DES, Constant.MAX_LENTH_TITLE_DES,
						description)) {
					request.setAttribute("err", MessageProperties.getMesage(Constant.ER003));
					request.setAttribute(Constant.NEWS, news);
					RequestDispatcher dispatch = request.getServletContext()
							.getRequestDispatcher(Constant.INSERT_NEWS_JSP);
					dispatch.forward(request, response);
				} else {
					// update vào db
					newsLogic.updateNews(news);
					// Ghi file upload
					fileImage.write(file);
					response.sendRedirect(
							request.getContextPath() + Constant.LIST_NEWS_AUTHOR_URL + "?action=updatesuccess");
				}

			} else {
				response.sendRedirect(request.getContextPath() + Constant.LOGOUT_URL);
			}

		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// Chuyển đến mh lỗi
			response.sendRedirect(request.getContextPath() + Constant.SYSTEM_ERR_URL);
		}

	}

}
