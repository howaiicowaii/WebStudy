package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.LoseCatVO;
public class LoseCatModel {
	@RequestMapping("losecat/losecat.do")
	public String losecat_losecat(HttpServletRequest request,HttpServletResponse response)
	{
		// 1. View한테 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 2. DB 연동
		LoseCatDAO dao=new LoseCatDAO();
		List<LoseCatVO> list=dao.loseCatListData(curpage);
		int totalpage=dao.loseCatTotalPage();
		
		request.setAttribute("page", page);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("clist", list);
		// 결과값 모아서 request에 저장
		request.setAttribute("main_jsp", "../losecat/losecat.jsp");
		return "../main/main.jsp";
	}
}
