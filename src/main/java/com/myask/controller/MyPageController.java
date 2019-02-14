package com.myask.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myask.domain.AskVO;
import com.myask.domain.UserVO;
import com.myask.mapper.AskMapper;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	private final String myAskLink = "http://myask.cf/ask/";
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AskMapper askMapper;
	
	// 마이페이지
	@GetMapping("/{id}")
	public ModelAndView mypage(HttpSession session) throws Exception {
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		// 새로운 질문이 있다면 리스트에 저장
		List<AskVO> newAskVOList = null;
		int newAskCount = userMapper.newAskCount(loginId);
		if(0 < newAskCount) {
			newAskVOList = askMapper.listNewAsk(loginId);
		}
		
		// 답변이 완료된 ask 리스트 
		List<AskVO> completedAskVOList = askMapper.listCompletedAsk(loginId);
		
		ModelAndView mav = new ModelAndView(Jsp.MY);
		mav.addObject("myAskLink", myAskLink + loginId);
		mav.addObject("newAskCount", newAskCount);
		mav.addObject("myId", loginId);
		mav.addObject("myName", loginVO.getName());
		mav.addObject("newAskVOList", newAskVOList);
		mav.addObject("completedAskVOList", completedAskVOList);
		
		return mav;
	}
	
	// 마이페이지 답변페이지
	@GetMapping("/{id}/{ask_code}")
	public ModelAndView replyPage(HttpSession session,
			@PathVariable("id") String id,
			@PathVariable("ask_code") long ask_code) throws Exception {
		String loginId = ((UserVO) session.getAttribute(Attr.LOGIN)).getId();
		AskVO askVO = askMapper.selectAsk(loginId, ask_code);
		if(askVO == null) {
			return new ModelAndView(Jsp.BAD_ACCESS, "myId", loginId);
		}
		
		ModelAndView mav = new ModelAndView(Jsp.REPLY);
		mav.addObject("askVO", askVO);
		mav.addObject("myId", loginId);
		mav.addObject("ask_code", ask_code);
		return mav;
	}
	
	// 답변페이지 답변 요청
	@PostMapping("/{id}/{ask_code}")
	public String reply(HttpSession session,
			@ModelAttribute AskVO formRequest,
			@PathVariable("id") String id,
			@PathVariable("ask_code") long ask_code) throws Exception {
		
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		// 로그인 아이디와 URL 아이디가 다른 경우 비정상접근 처리 : 비정상요청 
		if(!loginId.equals(id)) {
			return "redirect:/bad_request";
		}
		
		// 답변을 썼지만 이미 답변이 등록된 경우 저장하지 않고 리턴 : 비정상적인 방법
		AskVO askVO = askMapper.selectAsk(id, ask_code);
		if(askVO.getReply() != null) {
			return "redirect:/bad_request";
		}
		
		// 답변이 비어있다면 답변 넣고 저장
		askVO.setReply(formRequest.getReply());
		askMapper.updateAsk(askVO);
		return "redirect:/mypage/" + id;
	}
	
	// 마이페이지 삭제 요청
	@DeleteMapping("/{id}/{ask_code}")
	public String deleteAsk(HttpSession session,
			@PathVariable("id") String id, 
			@PathVariable("ask_code") long ask_code) throws Exception {
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();
		
		AskVO askVO = askMapper.selectAsk(loginId, ask_code);
		if(askVO == null) {
			return "redirect:/bad_request";
		}
		askMapper.deleteAsk(ask_code);
		return "redirect:/mypage/" + loginId;
	}
	
	// 로그아웃 요청
	@PostMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		if(session.getAttribute(Attr.LOGIN) != null) {
			session.removeAttribute(Attr.LOGIN);
		}
		
		return "redirect:/login";
	}
}
