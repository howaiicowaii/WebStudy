<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 실행한 다음 사이트 주소 => 맨 마지막에 있는 파일이 request를 갖고 있다
	String id=request.getParameter("id");
    String pwd=request.getParameter("pwd");
    String mode=request.getParameter("mode");
    if(Integer.parseInt(mode)==1)
    {
    	response.sendRedirect("e.jsp"); // 화면 이동
    }
    else
    {
    	pageContext.forward("e.jsp"); // 둘 다 화면이동 but, forward는 페이지 유지(덮어쓰는 느낌)(request 값 유지된다)
    	// <jsp:forward page="">
    	// 
    }
%>