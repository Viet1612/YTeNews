package ytebnews.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ytebnews.entities.User;
import ytebnews.logics.UserLogic;
import ytebnews.logics.impl.UserLogicImpl;
import ytebnews.utils.Constant;

/**
 * Servlet Filter implementation class LoginAuFilter
 */
@WebFilter("*.at")
public class LoginAuFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginAuFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		try {
			// Khai báo session lấy session
			HttpSession session = req.getSession();
			UserLogic userLogic = new UserLogicImpl();
			// lấy loginname từ sesion
			String loginName = (String) session.getAttribute(Constant.LOGIN_NAME);
			// Biến check cho qua filter
			boolean isDo = false;
			// Đường dẫn sendRedirect
			String path = Constant.LOGIN_URL;
			// Lấy servletPath
			String servletPath = req.getServletPath();
			User user = userLogic.getUserByLoginName(loginName);
			// Check giá trị session và là admin
			if (loginName != null) {
				if (user != null && user.getRule() == Constant.RULE_AUTHOR) {
					isDo = true;
				} else {
					// Xóa session
					session.invalidate();
				}

			} else if (Constant.LOGIN_URL.equals(servletPath)) { // Link MH login cho qua chưa đăng nhập
				isDo = true;
			}
			if (isDo) {
				// Cho qua filter
				chain.doFilter(request, response);
			} else {
				// sendRedirect
				rep.sendRedirect(req.getContextPath() + path);
			}
		} catch (Exception e) {
			System.out.println(this.getClass().getName() + "-"
					+ Thread.currentThread().getStackTrace()[1].getMethodName() + e.getMessage());
			// chyển đến mh lỗi
			rep.sendRedirect(req.getContextPath() + Constant.SYSTEM_ERR_URL);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
