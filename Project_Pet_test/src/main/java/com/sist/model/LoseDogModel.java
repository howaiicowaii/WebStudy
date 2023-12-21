package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class LoseDogModel {
	@RequestMapping("losedog/losedog.do")
	public String losedog_list(HttpServletRequest request,HttpServletResponse response)
	{
		// 1. View 에서 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 2. DB 연동
		LoseDogDAO dao=LoseDogDAO.newInstance();
		List<LoseDogVO> list=dao.loseDogListData(curpage);
		int totalpage=dao.loseDogTotalPage();
		
		request.setAttribute("ldList", list);
		request.setAttribute("main_jsp", "../losedog/losedog.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("losedog/losedogdetail.do")
	public String losedog_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String ldno=request.getParameter("ldno");
		// DAO 연결
		LoseDogDAO dao=LoseDogDAO.newInstance();
		LoseDogVO ldvo=dao.loseDogDetailData(Integer.parseInt(ldno));
		
		
		request.setAttribute("ldvo", ldvo);
		request.setAttribute("main_jsp", "../losedog/losedogdetail.jsp");
		return "../main/main.jsp";
	}
}
