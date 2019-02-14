package com.myask.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myask.domain.UserVO;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;
import com.myask.util.UserUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserMapper userMapper;
	
	// 로그인 페이지
	@GetMapping()
	public ModelAndView loginPage() throws Exception {
		return new ModelAndView(Jsp.LOGIN);
	}
	
	// 로그인 페이지 로그인 요청
	@PostMapping()
	public String login(@ModelAttribute UserVO formRequest, 
			HttpSession session) throws Exception {
		if(session.getAttribute(Attr.LOGIN) != null) {
			session.removeAttribute(Attr.LOGIN);
		}
		
		String id = formRequest.getId();
		String pw = formRequest.getPassword();
		
		String encryptedPw = UserUtil.getInstance().encrypt(id, pw);
		formRequest.setPassword(encryptedPw);
		
		UserVO loginReqVO = userMapper.selectUser(formRequest);
		boolean loginFailure = loginReqVO == null ? true : false;
		
		if(loginFailure) {
			session.setAttribute(Attr.BAD_ACCOUNT, true);
			new Thread(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				session.removeAttribute(Attr.BAD_ACCOUNT);
			}).start();
			return "redirect:/login";
		}
		
		session.setAttribute(Attr.LOGIN, loginReqVO);
		return "redirect:/mypage/" + id;
	}
}
