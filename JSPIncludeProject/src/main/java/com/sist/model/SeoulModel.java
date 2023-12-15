package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.SeoulVO;
public class SeoulModel {
	@RequestMapping("seoul/location.do")
	public String seoul_location(HttpServletRequest request,
			HttpServletResponse response)
	{
		// DB 연동
		// 1. 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 2. DB 연동 
		SeoulDAO dao=new SeoulDAO(); // 싱글턴 해야되는데 안만들어놔서 그냥 메모리 할당했다.
		List<SeoulVO> list=dao.seoulLocationListData(curpage,"seoul_location");
		int totalpage=dao.seoulLocationTotalPage("seoul_location");
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		// 3. 결과값 모아서 request에 저장
		request.setAttribute("main_jsp", "../seoul/location.jsp"); // main_jsp 에 include 했으니 return 은 다 main.jsp?
		return "../main/main.jsp";
	}
	
	@RequestMapping("seoul/nature.do")
	public String seoul_nature(HttpServletRequest request,
			HttpServletResponse response)
	{
		// DB 연동
		// 1. 요청값 받기
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// 2. DB 연동 
		SeoulDAO dao=new SeoulDAO(); // 싱글턴 해야되는데 안만들어놔서 그냥 메모리 할당했다. (12/15 만들었다)
		System.out.println("seoul-dao="+dao);
		List<SeoulVO> list=dao.seoulLocationListData(curpage,"seoul_nature");
		int totalpage=dao.seoulLocationTotalPage("seoul_nature");
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		// 3. 결과값 모아서 request에 저장
		request.setAttribute("main_jsp", "../seoul/nature.jsp"); // main_jsp 에 include 했으니 return 은 다 main.jsp?
		return "../main/main.jsp";
	}
	
	
}
