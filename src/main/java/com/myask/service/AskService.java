package com.myask.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myask.domain.AskVO;

public interface AskService {
	
	public int getNewAskCount(String id) throws Exception;
	
	public List<AskVO> listNewAsk(String id) throws Exception;
	
	public List<AskVO> listCompletedAsk(String id) throws Exception;
	
	public String delete(String pathId, String loginId, long ask_code, HttpServletResponse response) throws Exception;
	
	public String saveAsk(AskVO askVO, HttpSession session, HttpServletResponse response, String id) throws Exception;
}
