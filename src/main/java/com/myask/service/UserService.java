package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.myask.domain.UserVO;

@Service
public interface UserService {
	
	public boolean isNotExistUser(String id) throws Exception;
	
	public UserVO selectUserUsingId(String id) throws Exception;
	
	public String login(String id, String password, HttpSession session, HttpServletResponse response) throws Exception;
}
