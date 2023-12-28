package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request,HttpServletResponse response)
	{
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> aList=dao.foodBestListData("한식");
		List<FoodVO> bList=dao.foodBestListData("양식");
		List<FoodVO> cList=dao.foodBestListData("중식");
		List<FoodVO> dList=dao.foodBestListData("일식");
		
		request.setAttribute("aList", aList);
		request.setAttribute("bList", bList);
		request.setAttribute("cList", cList);
		request.setAttribute("dList", dList);
		
		request.setAttribute("main_jsp", "../main/home.jsp");
		return "../main/main.jsp";
	}
}
