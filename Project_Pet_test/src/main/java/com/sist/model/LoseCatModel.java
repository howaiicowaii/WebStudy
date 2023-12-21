package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class LoseCatModel {
	@RequestMapping("losecat/losecat.do")
	public String losecat_list(HttpServletRequest request,HttpServletResponse response)
	{
		// 1. 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 2. DB 연동
		LoseCatDAO dao=LoseCatDAO.newInstance();
		List<LoseCatVO> list=dao.losecatListData(curpage);
		int totalpage=dao.loseCatTotalPage();
		
		request.setAttribute("lcList", list);
		request.setAttribute("main_jsp", "../losecat/losecat.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("losecat/losecatdetail.do")
	public String losecat_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String lcno=request.getParameter("lcno");
		// DAO 연결
		LoseCatDAO dao=LoseCatDAO.newInstance();
		LoseCatVO lcvo=dao.loseCatDetailData(Integer.parseInt(lcno));
		
		request.setAttribute("lcvo", lcvo);
		request.setAttribute("main_jsp", "../losecat/losecatdetail.jsp");
		return "../main/main.jsp";
	}
}
