<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*"%>
<%
	try
	{
		request.setCharacterEncoding("UTF-8");
		String fn=request.getParameter("fn");
		File file=new File("c:\\download\\"+fn);
		response.setContentLength((int)file.length());
		// Header 전송 => 파일명, 파일크기 전송
		response.setHeader("Content-Disposition", 
				"attachment;filename="
				+URLEncoder.encode(fn,"UTF-8")); // attachment => 창 띄우기 (다운로드)
		BufferedInputStream bis=
				new BufferedInputStream(new FileInputStream(file));
		// 서버에서 파일 읽기 
		BufferedOutputStream bos=
				new BufferedOutputStream(
						response.getOutputStream());
		//              사용자의 다운로드 폴더..
		// 멀티 => Spring 배울 때 배울 듯 하다
		// 클라이언트로 파일을 보낸다 
		byte[] buffer=new byte[1024];
		int i=0;
		while((i=bis.read(buffer, 0, 1024))!=-1) // 문장 끝날 때까지 읽어 온다
		{
			bos.write(buffer,0,i);
		}
		bos.close();
		bis.close();
		out.clear(); 
		out=pageContext.pushBody(); 
		// out을 원래 상태로 복귀 => 이게 있어야 에러 없다
	}catch(Exception ex){}
%>
