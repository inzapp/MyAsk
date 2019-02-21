package com.myask.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myask.domain.LoginDTO;
import com.myask.service.UserServiceImpl;
import com.myask.util.Jsp;
import com.myask.util.UserUtil;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private UserUtil userUtil;
	
	// 로그인 페이지
	@GetMapping()
	public String loginPage() throws Exception {
		return Jsp.LOGIN;
	}
	
	// 로그인 페이지 로그인 요청
	@PostMapping()
	public String login(@ModelAttribute LoginDTO loginDTO, 
			HttpSession session, HttpServletResponse response) throws Exception {
		
		String id = loginDTO.getId();
		String password = userUtil.encrypt(id, loginDTO.getPassword());
		
		return userService.login(id, password, session, response);
	}
}
