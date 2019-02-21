package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myask.domain.LoginDTO;

public interface UserService {
	
	public boolean isNotExistUser(String id) throws Exception;
	
	public boolean login(String id, String password, HttpSession session, HttpServletResponse response) throws Exception;
}
