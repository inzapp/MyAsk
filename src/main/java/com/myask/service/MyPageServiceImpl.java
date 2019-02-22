package com.myask.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.myask.domain.AskVO;
import com.myask.domain.ReplyDTO;
import com.myask.domain.UserVO;
import com.myask.mapper.AskMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;

@Service
public class MyPageServiceImpl implements MyPageService {

	private final String myAskLink = "http://localhost/ask/";

	@Autowired
	private AskMapper askMapper;

	// 마이페이지
	@Override
	public String mypage(Model model, HttpSession session) throws Exception {

		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		int newAskCount = askMapper.selectNewAskCount(loginId);

		List<AskVO> newAskVOList = askMapper.listNewAsk(loginId);
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
	@Override
	public String replyPage(Model model, HttpSession session, long pathAskCode) throws Exception {

		String loginId = ((UserVO) session.getAttribute(Attr.LOGIN)).getId();
		AskVO askVO = askMapper.selectAsk(loginId, pathAskCode);

		boolean isNotExistAsk = askVO == null;
		if (isNotExistAsk) {
			model.addAttribute("myId", loginId);
			return Jsp.BAD_ACCESS;
		}

		model.addAttribute("askVO", askVO);
		model.addAttribute("myId", loginId);
		model.addAttribute("ask_code", pathAskCode);

		return Jsp.REPLY;
	}

	// 답변페이지 답변 요청
	@Override
	public String reply(HttpSession session, HttpServletResponse response, ReplyDTO formRequest, String pathId,
			long pathAskCode) throws Exception {
		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();

		// 로그인 아이디와 URL 아이디가 다른 경우 비정상접근 처리 : 비정상요청
		if (!loginId.equals(pathId))
			return "redirect:/bad_request";

		// 답변을 썼지만 이미 답변이 등록된 경우 저장하지 않고 리턴 : 비정상적인 방법
		AskVO askVO = askMapper.selectAsk(pathId, pathAskCode);
		if (askVO.getReply() != null)
			return "redirect:/bad_request";

		// 답변이 비어있다면 답변 넣고 저장
		askVO.setReply(formRequest.getReply());
		askMapper.updateAsk(askVO);
		return "redirect:/mypage/" + pathId;
	}

	// 답변이 안된 질문 삭제 요청
	@Override
	public String deleteAsk(HttpSession session, HttpServletResponse response, String pathId, long askCode)
			throws Exception {

		UserVO loginVO = (UserVO) session.getAttribute(Attr.LOGIN);
		String loginId = loginVO.getId();
		
		// pathId 와 login된 아이디가 일치하지 않는 경우 : 조작된 요청
		if (!pathId.equals(loginId)) {
			badAccess(response, loginId);
			return null;
		}

		AskVO askVO = askMapper.selectAsk(loginId, askCode);
		if (askVO == null) {
			badAccess(response, loginId);
			return null;
		}

		askMapper.deleteAsk(askCode);
		return "redirect:/mypage/" + loginId;
	}

	private void badAccess(HttpServletResponse response, String loginId) throws Exception {
		response.setContentType("text/html; charset=UTF-8;");
		
		PrintWriter pw = response.getWriter();
		pw.println("<script>alert('잘못된 요청입니다.'); location.href='/mypage/" + loginId + "'</script>");
		pw.flush();
		
	}

	// 로그아웃 요청
	@Override
	public String logout(HttpSession session, HttpServletResponse response) throws Exception {

		if (session.getAttribute(Attr.LOGIN) != null)
			session.removeAttribute(Attr.LOGIN);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<script>alert('로그아웃되었습니다.'); location.href='/login';</script>");
		pw.flush();
		
		return null;
	}
}
