package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.myask.domain.AccountCheckDTO;

@Service
public interface SignUpService {

	public String signupPage() throws Exception;

	public String signup(HttpSession session, HttpServletResponse response, AccountCheckDTO formRequest) throws Exception;
}
