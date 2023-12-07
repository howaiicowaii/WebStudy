<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.oreilly.servlet.multipart.*"%>
<%@ page import="com.oreilly.servlet.*" %>
<%
	String path=application.getRealPath("/")+"images";
	String enctype="UTF-8";
	int max_size=1024*1024*100;
	MultipartRequest mr=
			new MultipartRequest(request,path,max_size,enctype,
					new DefaultFileRenamePolicy()); // 같은 파일 여러번 다운받으면 뒤에 (1) 붙는 거
	String fn=mr.getOriginalFileName("upload");
	response.sendRedirect("list.jsp?fn="+fn);
					
%>