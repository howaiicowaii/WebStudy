<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*"%>
<%
	// request.setCharacterEncoding("UTF-8");
	// => POST / GET 방식은 톰캣 자체에서 처리해준다
	String fd=request.getParameter("fd");
	String ss=request.getParameter("ss");
	String fno=request.getParameter("fno");
	// 1. Cookie 생성 
	Cookie cookie=new Cookie("food_"+fno,fno); // 같은 게 쿠키에 저장이 안되게 "food_"+fno (키는 중복되면 안되니깐 1개만 저장되게끔)
	// 2. 저장 경로 설정 
	cookie.setPath("/");
	// 3. 저장 기간 설정 (초단위)
	cookie.setMaxAge(60*60*24);
	// 4. 브라우저로 전송
	response.addCookie(cookie);
	
	// 5. 상세보기로 이동
	response.sendRedirect("detail.jsp?fno="+fno+"&fd="+fd+"&ss="+URLEncoder.encode(ss,"UTF-8"));
	// sendRedirect 는 GET 방식 => 요청값 전송할 때 반드시 ? 이용해서 데이터 보내야 한다 
%>