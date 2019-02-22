package com.myask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myask.util.Jsp;

@Controller
@RequestMapping("/bad_request")
public class BadRequestController {
	
	@GetMapping()
	public String badRequestPage() throws Exception {
		return Jsp.BAD_ACCESS;
	}
}
