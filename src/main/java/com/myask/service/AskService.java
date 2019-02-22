package com.myask.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.myask.domain.AskDTO;

@Service
public interface AskService {
	
	public String askPage(Model model,String id) throws Exception;
	
	public String ask(HttpSession session, AskDTO askDTO, String id, HttpServletResponse response) throws Exception;
}
