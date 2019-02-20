package com.myask.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myask.domain.AskVO;
import com.myask.domain.UserVO;
import com.myask.mapper.AskMapper;
import com.myask.mapper.UserMapper;
import com.myask.service.UserServiceImpl;
import com.myask.util.Attr;
import com.myask.util.Jsp;

@Controller
@RequestMapping("/ask")
public class AskController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AskMapper askMapper;
	
	@Autowired
	private UserServiceImpl userService;
	
	// 질문 페이지
	@GetMapping("/{id}")
	public String askPage(Model model, @PathVariable("id") String id) throws Exception {
		if(userService.isNotExistUser(id))
			return Jsp.BAD_ACCESS;
		
		UserVO userVO = userMapper.selectUserUsingId(id);
		if(userVO == null) 
			return Jsp.BAD_ACCESS;
		
		List<AskVO> completedAskVOList = askMapper.listCompletedAsk(id);
		model.addAttribute("userVO", userVO);
		model.addAttribute("completedAskVOList", completedAskVOList);
		return Jsp.ASK;
	}
	
	// 질문 페이지 질문 요청
	@PostMapping("/{id}")
	public String ask(HttpSession session,
			@ModelAttribute AskVO formRequest,
			@PathVariable("id") String id) throws Exception {
		
		if(userService.isNotExistUser(id))
			return "redirect:/bad_request";
		
		formRequest.setParent_id(id);
		askMapper.insertAsk(formRequest);
		
		session.setAttribute(Attr.ASK_DELAY, true);
		new Thread(() -> {
			try {
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			session.removeAttribute(Attr.ASK_DELAY);
		}).start();
		
		return "redirect:/ask/" + id;
	}
}
