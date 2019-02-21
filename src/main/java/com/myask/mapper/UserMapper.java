package com.myask.mapper;

import org.apache.ibatis.annotations.Param;

import com.myask.domain.UserVO;

public interface UserMapper {
	public void insertUser(@Param("userVO") UserVO requestVO) throws Exception;
	
	public UserVO selectUser(@Param("id") String id, @Param("pw") String pw) throws Exception;
	
	public UserVO selectUserUsingId(@Param("id") String id) throws Exception;
	
	public int newAskCount(@Param("id") String id) throws Exception;
	
	public void update(@Param("userVO") UserVO requestVO) throws Exception;
	
	public void deleteUser(@Param("id") String id) throws Exception;
}
