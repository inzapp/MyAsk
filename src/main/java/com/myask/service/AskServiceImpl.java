package com.myask.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.myask.domain.AskDTO;
import com.myask.domain.AskVO;
import com.myask.domain.UserVO;
import com.myask.mapper.AskMapper;
import com.myask.mapper.UserMapper;
import com.myask.util.Attr;
import com.myask.util.Jsp;

@Service
public class AskServiceImpl implements AskService {

	@Autowired
	private AskMapper askMapper;

	@Autowired
	private UserMapper userMapper;

	// 질문 페이지
	@Override
	public String askPage(Model model, String id) throws Exception {

		UserVO userVO = userMapper.selectUserUsingId(id);
		boolean isNotExistUser = userVO == null;
		if (isNotExistUser)
			return Jsp.BAD_ACCESS;

		List<AskVO> completedAskVOList = askMapper.listCompletedAsk(id);
		model.addAttribute("userVO", userVO);
		model.addAttribute("completedAskVOList", completedAskVOList);
		return Jsp.ASK;
	}

	// 질문 페이지 질문 요청
	@Override
	public String ask(HttpSession session, AskDTO askDTO, String id, HttpServletResponse response) throws Exception {

		UserVO userVO = userMapper.selectUserUsingId(id);
		boolean isNotExistUser = userVO == null;
		if (isNotExistUser)
			return "redirect:/bad_request";

		String ask = askDTO.getAsk();
		AskVO askVO = new AskVO();
		askVO.setParent_id(id);
		askVO.setAsk(ask);

		askMapper.insertAsk(askVO);

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
