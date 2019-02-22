package com.myask.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myask.domain.AccountCheckDTO;
import com.myask.service.SignUpService;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private SignUpService signUpService;

	// 회원가입 페이지
	@GetMapping()
	public String signupPage() throws Exception {
		
		return signUpService.signupPage();
	}

	// 회원가입 페이지 회원가입 요청
	@PostMapping()
	public String signup(HttpSession session, HttpServletResponse response, @ModelAttribute AccountCheckDTO formRequest)
			throws Exception {
		
		return signUpService.signup(session, response, formRequest);
	}
}
