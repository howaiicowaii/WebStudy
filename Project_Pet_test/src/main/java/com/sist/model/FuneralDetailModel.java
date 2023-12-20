package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

public class FuneralDetailModel {
	@RequestMapping("FuneralDetail/produce.do")
	public String funeral_main(HttpServletRequest request, HttpServletResponse response)
	{
		// DB 연동
	      // 1. 요청값 받기
          //없는듯???
	      // 2. DB 연동 
	      FuneralDetailDAO dao=FuneralDetailDAO.newInstance();
	      //System.out.println("funeralDetailDAO-dao="+dao);
	      List<FuneralDetailVO> list=dao.FuneralListData();
	      
	      // 3. 결과값 모아서 request에 저장
	      request.setAttribute("list", list);
		  request.setAttribute("main_jsp", "../funeralDetail/produce.jsp");
		  return "../main/main.jsp";
	}
	
	@RequestMapping("FuneralDetail/reserve.do")
	public String funeral_reserve(HttpServletRequest request, HttpServletResponse response)
	{
	      // 3. 결과값 모아서 request에 저장
		  request.setAttribute("main_jsp", "../funeralDetail/reserve.jsp");
		  return "../main/main.jsp";
	}
}
