package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;
public class AnimalModel {
	@RequestMapping("animal/animal.do")
	public String animal_animal(HttpServletRequest request, HttpServletResponse response)
	{
		// 요청값 ??
		// 1. View한테 요청값 받기
				String page=request.getParameter("page");
				if(page==null)
					page="1";
				int curpage=Integer.parseInt(page);
				// 2. DB 연동
		AnimalDAO dao=AnimalDAO.newInstance(); // 싱글턴
		List<AnimalVO> list=dao.animalListData(curpage);
		int totalpage=dao.animalTotalPage();

		request.setAttribute("anilist", list);
		request.setAttribute("main_jsp", "../animal/animal.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("animal/animaldetail.do")
	public String animal_detail(HttpServletRequest request,HttpServletResponse response)
	{
		String kano=request.getParameter("kano");
		// DAO 연결
		AnimalDAO dao=AnimalDAO.newInstance();
		AnimalVO avo=dao.animalDetailData(Integer.parseInt(kano));
		
		
		request.setAttribute("avo", avo);
		request.setAttribute("main_jsp", "../animal/animaldetail.jsp");
		return "../main/main.jsp";
	}
}
