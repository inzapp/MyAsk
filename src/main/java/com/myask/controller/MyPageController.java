package com.myask.controller;

import java.util.List;

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
	public String mypage(Model model, HttpSession session) throws Exception {
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		// 새로운 질문이 있다면 리스트에 저장
		List<AskVO> newAskVOList = null;
		int newAskCount = userMapper.newAskCount(loginId);
		
		if (0 < newAskCount) 
			newAskVOList = askMapper.listNewAsk(loginId);

		// 답변이 완료된 ask 리스트
		List<AskVO> completedAskVOList = askMapper.listCompletedAsk(loginId);

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
		AskVO askVO = askMapper.selectAsk(loginId, ask_code);
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
	public String reply(HttpSession session, @ModelAttribute AskVO formRequest, @PathVariable("id") String id,
			@PathVariable("ask_code") long ask_code) throws Exception {

		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		// 로그인 아이디와 URL 아이디가 다른 경우 비정상접근 처리 : 비정상요청
		if (!loginId.equals(id)) {
			return "redirect:/bad_request";
		}

		// 답변을 썼지만 이미 답변이 등록된 경우 저장하지 않고 리턴 : 비정상적인 방법
		AskVO askVO = askMapper.selectAsk(id, ask_code);
		if (askVO.getReply() != null) {
			return "redirect:/bad_request";
		}

		// 답변이 비어있다면 답변 넣고 저장
		askVO.setReply(formRequest.getReply());
		askMapper.updateAsk(askVO);
		return "redirect:/mypage/" + id;
	}

	// 마이페이지 삭제 요청
	@DeleteMapping("/{id}/{ask_code}")
	public String deleteAsk(HttpSession session, @PathVariable("id") String id, @PathVariable("ask_code") long ask_code)
			throws Exception {
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		AskVO askVO = askMapper.selectAsk(loginId, ask_code);
		if (askVO == null) {
			return "redirect:/bad_request";
		}
		askMapper.deleteAsk(ask_code);
		return "redirect:/mypage/" + loginId;
	}

	// 로그아웃 요청
	@PostMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		if (session.getAttribute(Attr.LOGIN) != null)
			session.removeAttribute(Attr.LOGIN);

		return "redirect:/login";
	}
}
