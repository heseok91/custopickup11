package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserAdminInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String auth = (String) session.getAttribute("usertype");
		
		if (auth == null || !auth.equals("admin") ) {
			response.sendRedirect(request.getContextPath() + "/user/userLoginForm?usertype=forbidden");
			return false;
			
		} else {
			return true;
		}
	}

}
