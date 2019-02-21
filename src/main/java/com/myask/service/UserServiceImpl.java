package com.myask.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myask.domain.UserVO;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.UserUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserUtil userUtil;

	@Override
	public boolean isNotExistUser(String id) throws Exception {
		return userMapper.selectUserUsingId(id) == null;
	}

	@Override
	public boolean login(String id, String password, HttpSession session, HttpServletResponse response)
			throws Exception {

		UserVO loginVO = userMapper.selectUser(id, password);
		boolean loginSuccess = loginVO != null;

		if (!loginSuccess) {
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter pw = response.getWriter();

			UserVO testVO = userMapper.selectUserUsingId(id);
			if (testVO == null)
				pw.println("<script>alert('존재하지 않는 아이디 입니다.'); location.href='/login'</script>");
			else
				pw.println("<script>alert('비밀번호가 틀렸습니다.'); location.href='/login'</script>");

			return false;
		}

		if (session.getAttribute(Attr.LOGIN) != null)
			session.removeAttribute(Attr.LOGIN);

		session.setAttribute(Attr.LOGIN, loginVO);
		return true;
	}

}
