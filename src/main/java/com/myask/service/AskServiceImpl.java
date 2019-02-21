package com.myask.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myask.domain.AskVO;
import com.myask.mapper.AskMapper;
import com.myask.util.Attr;

@Service
public class AskServiceImpl implements AskService {

	@Autowired
	private AskMapper askMapper;

	@Override
	public int getNewAskCount(String id) throws Exception {
		return askMapper.selectNewAskCount(id);
	}

	@Override
	public List<AskVO> listNewAsk(String id) throws Exception {
		return askMapper.listNewAsk(id);
	}

	@Override
	public List<AskVO> listCompletedAsk(String id) throws Exception {
		return askMapper.listCompletedAsk(id);
	}

	@Override
	public String delete(String pathId, String loginId, long ask_code, HttpServletResponse response) throws Exception {
		
		// pathId 와 login된 아이디가 일치하지 않는 경우 : 조작된 요청
		if(!pathId.equals(loginId)) {
			badAccess(response, loginId);
			return null;
		}
		
		AskVO askVO = askMapper.selectAsk(loginId, ask_code);
		if(askVO == null) {
			badAccess(response, loginId);
			return null;
		}
		
		askMapper.deleteAsk(ask_code);
		return "redirect:/mypage/" + loginId;
	}
	
	private void badAccess(HttpServletResponse response, String loginId) throws Exception {
		response.setContentType("text/html; charset=UTF-8;");
		
		PrintWriter pw = response.getWriter();
		pw.println("<script>alert('잘못된 요청입니다.'); location.href='/mypage/" + loginId + "'</script>");
		pw.flush();
		
	}

	@Override
	public String saveAsk(AskVO askVO, HttpSession session, 
			HttpServletResponse response, String id) throws Exception {
		
		askMapper.insertAsk(askVO);
		
		session.setAttribute(Attr.ASK_DELAY, true);
		new Thread(() ->  {
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
