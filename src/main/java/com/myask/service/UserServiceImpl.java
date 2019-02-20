package com.myask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myask.domain.UserVO;
import com.myask.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean isNotExistUser(String id) throws Exception {
		return userMapper.selectUserUsingId(id) == null;
	}

	@Override
	public boolean login(String id, String password) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setPassword(password);
		
		return userMapper.selectUser(userVO) != null;
	}
	
	
}
