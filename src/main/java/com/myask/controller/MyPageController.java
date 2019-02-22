package com.myask.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myask.domain.ReplyDTO;
import com.myask.service.MyPageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	@Autowired
	private MyPageService myPageService;

	// 마이페이지
	@GetMapping("/{id}")
	public String mypage(Model model, HttpSession session) throws Exception {

		return myPageService.mypage(model, session);
	}

	// 마이페이지 답변페이지
	@GetMapping("/{id}/{askCode}")
	public String replyPage(Model model, HttpSession session, @PathVariable("id") String pathId,
			@PathVariable("askCode") long pathAskCode) throws Exception {

		return myPageService.replyPage(model, session, pathAskCode);
	}

	// 답변페이지 답변 요청
	@PostMapping("/{id}/{askCode}")
	public String reply(HttpSession session, HttpServletResponse response, @ModelAttribute ReplyDTO formRequest,
			@PathVariable("id") String pathId, @PathVariable("askCode") long pathAskCode) throws Exception {

		return myPageService.reply(session, response, formRequest, pathId, pathAskCode);
	}

	// 답변이 안된 질문 삭제 요청
	@DeleteMapping("/{pathId}/{askCode}")
	public String deleteAsk(HttpSession session, HttpServletResponse response, @PathVariable("pathId") String pathId,
			@PathVariable("askCode") long askCode) throws Exception {

		return myPageService.deleteAsk(session, response, pathId, askCode);
	}

	// 로그아웃 요청
	@PostMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) throws Exception {

		return myPageService.logout(session, response);
	}
}
