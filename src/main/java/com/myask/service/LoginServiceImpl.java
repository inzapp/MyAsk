package com.myask.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myask.domain.LoginDTO;
import com.myask.domain.UserVO;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AccountUtil userUtil;

	// 로그인 페이지
	@Override
	public String loginPage() throws Exception {
		return Jsp.LOGIN;
	}

	// 로그인 요청
	@Override
	public String login(LoginDTO loginDTO, HttpSession session, HttpServletResponse response) throws Exception {

		String id = loginDTO.getId();
		String password = userUtil.encrypt(id, loginDTO.getPassword());
		UserVO loginVO = userMapper.selectUser(id, password);
		
		boolean loginFailure = loginVO == null;
		if (loginFailure) {
			response.setContentType("text/html; charset=UTF-8;");
			PrintWriter pw = response.getWriter();

			UserVO idExistTestVO = userMapper.selectUserUsingId(id);
			
			boolean idNotExist = idExistTestVO == null;
			if (idNotExist)
				pw.println("<script>alert('존재하지 않는 아이디 입니다.'); location.href='/login'</script>");
			else
				pw.println("<script>alert('비밀번호가 틀렸습니다.'); location.href='/login'</script>");
			
			pw.flush();
			return null;
		}

		if (session.getAttribute(Attr.LOGIN) != null)
			session.removeAttribute(Attr.LOGIN);

		session.setAttribute(Attr.LOGIN, loginVO);
		return "redirect:/mypage/" + id;
	}

}
