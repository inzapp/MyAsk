package com.myask.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.myask.domain.AskVO;
import com.myask.domain.UserVO;

@Service
public interface AskService {
	
	public AskVO selectAsk(String parent_id, long ask_code) throws Exception;
	
	public int getNewAskCount(String id) throws Exception;
	
	public List<AskVO> listNewAsk(String id) throws Exception;
	
	public List<AskVO> listCompletedAsk(String id) throws Exception;
	
	public String delete(String pathId, String loginId, long ask_code, HttpServletResponse response) throws Exception;
	
	public String saveAsk(AskVO askVO, HttpSession session, HttpServletResponse response, String id) throws Exception;
	
	// TODO : DTO 수정 필요
	public String reply(UserVO loginVO, AskVO formRequest, String loginId, String pathId, long pathAskCode, HttpServletResponse response) throws Exception;
}
