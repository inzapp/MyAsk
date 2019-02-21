package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {
	
	public boolean isNotExistUser(String id) throws Exception;
	
	public String login(String id, String password, HttpSession session, HttpServletResponse response) throws Exception;
}
