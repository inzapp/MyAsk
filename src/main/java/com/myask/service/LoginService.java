package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.myask.domain.LoginDTO;

@Service
public interface LoginService {

	public String loginPage() throws Exception;
	
	public String login(LoginDTO loginDTO, HttpSession session, HttpServletResponse response) throws Exception;
}
