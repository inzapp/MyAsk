package com.myask.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myask.domain.AccountCheckDTO;
import com.myask.domain.UserVO;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;
import com.myask.util.pRes;

@Service
public class SignUpServiceImpl implements SignUpService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AccountUtil accountUtil;

	// 회원가입 페이지
	@Override
	public String signupPage() throws Exception {

		return Jsp.SIGNUP;
	}

	// 회원가입 요청
	@Override
	public String signup(HttpSession session, HttpServletResponse response, AccountCheckDTO formRequest)
			throws Exception {

		String id = formRequest.getId();
		String pw = formRequest.getPassword();
		String pw2 = formRequest.getPassword2();
		String name = formRequest.getName();

		// 비밀번호 , 비밀번호 확인이 일치하지 않으면 리턴
		if (!pw.equals(pw2)) {
			pRes.setThreadAttr(session, Attr.PW_INCORRECT);
			return "redirect:/signup";
		}

		// 아이디 비밀번호 글자수와 문자 조합 체크
		if (!accountUtil.isAccountCondition(id, pw, name)) {
			pRes.setThreadAttr(session, Attr.BAD_ACCOUNT);
			return "redirect:/signup";
		}

		// 가입하려는 아이디가 이미 존재하는 아이디인지 체크
		UserVO existIdCheckVO = userMapper.selectUserUsingId(id);
		boolean isExistId = existIdCheckVO != null;

		if (isExistId) {
			pRes.setThreadAttr(session, Attr.ID_ALREADY_EXIST);
			return "redirect:/signup";
		}

		// 문자조건과 아이디 중복체크 통과했다면 암호화 후 insert
		String encryptedPw = accountUtil.encrypt(id, pw);
		formRequest.setPassword(encryptedPw);

		// 체크용 VO에 담겨있던 정보를 UserVO 에 넣고 DB 인서트
		UserVO signupUserVO = new UserVO();
		signupUserVO.setId(formRequest.getId());
		signupUserVO.setPassword(formRequest.getPassword());
		signupUserVO.setName(formRequest.getName());

		userMapper.insertUser(signupUserVO);

		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter pWriter = response.getWriter();
		pWriter.println("<script>alert('회원가입에 성공하였습니다.'); location.href='/login'</script>");
		pWriter.flush();

		return null;
	}

}
