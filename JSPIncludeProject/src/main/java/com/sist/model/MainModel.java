package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
/*
	1. DispatcherServlet
	   => WEB-INF 에 있는 application에 있는 클래스명을 읽는다  "com.sist.model.MainModel"
	   => 대기
	   => init은 서버구동시 한번만 수행 ( service() 는 사용자 요청시마다 호출된다 )
	2. 사용자 접속 : service() => 사용자가 접속할 때마다 service() 메소드가 호출된다
	   => 사용자가 URL 전송
	      main/main.do
어노테이션?=> 전체 XML에 등록된 Model을 읽어서 
	      => @RequestMapping을 찾아서 메소드를 호출해준다 ( 그리고 넘어오는 request를 받아서 전송해준다 )
*/
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request,
				HttpServletResponse response)
	{
		request.setAttribute("main_jsp", "../main/home.jsp"); // request에 main_jsp key값 속에 home.jsp 값 담았다
		return "../main/main.jsp"; // 한테 보내라 출력해라
	}
}
