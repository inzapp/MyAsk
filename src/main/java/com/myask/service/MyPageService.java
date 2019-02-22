package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.myask.domain.ReplyDTO;

@Service
public interface MyPageService {

	public String mypage(Model model, HttpSession session) throws Exception;

	public String replyPage(Model model, HttpSession session, long pathAskCode) throws Exception; 

	public String reply(HttpSession session, HttpServletResponse response, ReplyDTO formRequest, String pathId, long pathAskCode) throws Exception;

	public String deleteAsk(HttpSession session, HttpServletResponse response, String pathId, long askCode) throws Exception;

	public String logout(HttpSession session, HttpServletResponse response) throws Exception;
}
