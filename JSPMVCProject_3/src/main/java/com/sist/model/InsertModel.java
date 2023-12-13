package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class InsertModel implements Model {

	@Override
	public String handelRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 화면만 이동
		return "board/insert.jsp";
	}

}
