package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
import com.sist.vo.*;
public class DetailModel implements Model {

	@Override
	public String handelRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String no=request.getParameter("no");
		// DAO
		BoardDAO dao=BoardDAO.newInstance();
		BoardVO vo=dao.boardDetail(Integer.parseInt(no));
		request.setAttribute("vo", vo); // detail.jsp 로 보낼 값 request에 저장
		// request
		return "board/detail.jsp";
	}

}
