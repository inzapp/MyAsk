package com.myask.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myask.domain.UserVO;
import com.myask.util.Attr;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		
		boolean isNotLoggedIn = loginVO == null;
		if (isNotLoggedIn) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.write("<script>alert('로그인이 필요합니다!'); location.href='/login'</script>");
			pw.flush();
			
			return false;
		}

		return true;
	}
}
