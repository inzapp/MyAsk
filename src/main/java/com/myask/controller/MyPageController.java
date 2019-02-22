package com.myask.controller;

import java.util.List;

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

import com.myask.domain.AskVO;
import com.myask.domain.UserVO;
import com.myask.mapper.AskMapper;
import com.myask.service.AskServiceImpl;
import com.myask.util.Attr;
import com.myask.util.Jsp;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

	private final String myAskLink = "http://localhost/ask/";
	
	@Autowired
	private AskServiceImpl askService;

	// 마이페이지
	@GetMapping("/{id}")
	public String mypage(Model model, HttpSession session) throws Exception {
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		int newAskCount = askService.getNewAskCount(loginId);
		List<AskVO> newAskVOList = askService.listNewAsk(loginId);
		List<AskVO> completedAskVOList = askService.listCompletedAsk(loginId);

		model.addAttribute("myAskLink", myAskLink + loginId);
		model.addAttribute("newAskCount", newAskCount);
		model.addAttribute("myId", loginId);
		model.addAttribute("myName", loginVO.getName());
		model.addAttribute("newAskVOList", newAskVOList);
		model.addAttribute("completedAskVOList", completedAskVOList);

		return Jsp.MY;
	}

	// 마이페이지 답변페이지
	@GetMapping("/{id}/{ask_code}")
	public String replyPage(Model model, HttpSession session, @PathVariable("id") String id,
			@PathVariable("ask_code") long ask_code) throws Exception {
		
		String loginId = ((UserVO) session.getAttribute(Attr.LOGIN)).getId();
		AskVO askVO = askService.selectAsk(loginId, ask_code);
		if (askVO == null) {
			model.addAttribute("myId", loginId);
			return Jsp.BAD_ACCESS;
		}

		model.addAttribute("askVO", askVO);
		model.addAttribute("myId", loginId);
		model.addAttribute("ask_code", ask_code);
		
		return Jsp.REPLY;
	}

	// 답변페이지 답변 요청
	@PostMapping("/{id}/{ask_code}")
	public String reply(HttpSession session, @ModelAttribute AskVO formRequest, @PathVariable("id") String pathId,
			@PathVariable("ask_code") long pathAskCode, HttpServletResponse response) throws Exception {

		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();
		
		return askService.reply(loginVO, formRequest, loginId, pathId, pathAskCode, response);
	}

	// 답변이 안된 질문 삭제 요청
	@DeleteMapping("/{path_id}/{ask_code}")
	public String deleteAsk(HttpSession session, @PathVariable("path_id") String pathId, 
			@PathVariable("ask_code") long ask_code, HttpServletResponse response)
			throws Exception {
		
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();
		
		return askService.delete(pathId, loginId, ask_code, response);
	}

	// 로그아웃 요청
	@PostMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		if (session.getAttribute(Attr.LOGIN) != null)
			session.removeAttribute(Attr.LOGIN);

		return "redirect:/login";
	}
}
