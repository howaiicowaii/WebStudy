<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// data:{"no":1}
	String index=request.getParameter("no");
	String img="../image/m"+index+".jpg";
%>
<%=img %>