package com.myask.service;

public interface UserService {
	
	public boolean isNotExistUser(String id) throws Exception;
	
	public boolean login(String id, String password) throws Exception;
}
