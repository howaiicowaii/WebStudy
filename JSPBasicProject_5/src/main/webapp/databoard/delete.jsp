<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.io.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"/>
<%
	String no=request.getParameter("no");
	// DAO 연동 
	DataBoardBean vo=dao.boardFileInfoData(Integer.parseInt(no));
	dao.boardDelete(Integer.parseInt(no)); // 오라클 삭제?
	// 파일 삭제
	try
	{
		if(vo.getFilesize()>0) // 파일이 존재한다면
		{
			File file=new File("c:\\download\\"+vo.getFilename());
			file.delete(); // 파일 삭제
		}
	}catch(Exception ex) {}
	// list.jsp로 이동
	response.sendRedirect("list.jsp");
%>
