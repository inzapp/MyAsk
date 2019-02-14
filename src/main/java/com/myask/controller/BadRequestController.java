package com.myask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myask.util.Jsp;

@Controller
@RequestMapping("/bad_request")
public class BadRequestController {
	
	@GetMapping()
	public ModelAndView badRequest() throws Exception {
		return new ModelAndView(Jsp.BAD_ACCESS);
	}
}
