package com.myask.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myask.domain.AccountCheckVO;
import com.myask.domain.UserVO;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;
import com.myask.util.UserUtil;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	private UserMapper userMapper;
	
	// 회원가입 페이지
	@GetMapping()
	public ModelAndView signupPage() throws Exception {
		return new ModelAndView(Jsp.SIGNUP);
	}
	
	// 회원가입 페이지 회원가입 요청
	@PostMapping()
	public String signup(@ModelAttribute AccountCheckVO formRequest, 
			HttpSession session) throws Exception {
		String id = formRequest.getId();
		String pw = formRequest.getPassword();
		String pw2 = formRequest.getPassword2();
		String name = formRequest.getName();
		
		// 비밀번호 , 비밀번호 확인이 일치하지 않으면 리턴
		if(!pw.equals(pw2)) {
			session.setAttribute(Attr.PW_INCORRECT, true);
			new Thread(() -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
				session.removeAttribute(Attr.PW_INCORRECT);
			}).start(); 
			return "redirect:/signup";
		}

		// 아이디 비밀번호 글자수와 문자 조합 체크
		if(!UserUtil.getInstance().isAccountCondition(id, pw, name)) {
			session.setAttribute(Attr.BAD_ACCOUNT, true);
			new Thread(() -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
				session.removeAttribute(Attr.BAD_ACCOUNT);
			}).start(); 
			return "redirect:/signup";
		}
		
		// 가입하려는 아이디가 이미 존재하는 아이디인지 체크
		UserVO existIdCheckVO = userMapper.selectUserUsingId(id);
		boolean isExistId = existIdCheckVO != null ? true : false;
		
		if(isExistId) {
			session.setAttribute(Attr.ID_ALREADY_EXIST, true);
			new Thread(() -> {
				try {
					Thread.sleep(500);
				} catch (InterruptedException ignored) {
				}
				session.removeAttribute(Attr.ID_ALREADY_EXIST);
			}).start(); 
			return "redirect:/signup";
		}
		
		// 문자조건과 아이디 중복체크 통과했다면 암호화 후 insert
		String encryptedPw = UserUtil.getInstance().encrypt(id, pw);
		formRequest.setPassword(encryptedPw);
		
		// 체크용 VO에 담겨있던 정보를 UserVO 에 넣고 DB 인서트
		UserVO signupUserVO = new UserVO();
		signupUserVO.setId(formRequest.getId());
		signupUserVO.setPassword(formRequest.getPassword());
		signupUserVO.setName(formRequest.getName());
		
		userMapper.insertUser(signupUserVO);
		
		session.setAttribute(Attr.SIGNUP_SUCCESS, true);
		new Thread(() -> {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			session.removeAttribute(Attr.SIGNUP_SUCCESS);
		}).start();
		return "redirect:/login";
	}
}
