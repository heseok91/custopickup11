package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String auth1 = (String) session.getAttribute("userid");
		
		if (auth1 == null) {
			response.sendRedirect(request.getContextPath() + "/user/userLoginForm?userid=login");
			return false;
		} else {
			return true;
		}
	}
	
}
