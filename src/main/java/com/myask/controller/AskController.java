package com.myask.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myask.domain.AskDTO;
import com.myask.service.AskService;

@Controller
@RequestMapping("/ask")
public class AskController {
	
	@Autowired
	private AskService askService;

	// 질문 페이지
	@GetMapping("/{id}")
	public String askPage(Model model, @PathVariable("id") String id) throws Exception {

		return askService.askPage(model, id);
	}

	// 질문 페이지 질문 요청
	@PostMapping("/{id}")
	public String ask(HttpSession session, @ModelAttribute AskDTO askDTO, @PathVariable("id") String id,
			HttpServletResponse response) throws Exception {

		return askService.ask(session, askDTO, id, response);
	}
}
