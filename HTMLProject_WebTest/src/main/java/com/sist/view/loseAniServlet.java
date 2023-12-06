package com.sist.view;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;

@WebServlet("/loseAniServlet")
public class loseAniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 브라우저를 통해서 요청시에 처리해서 브라우저로 HTML을 전송하는 메소드 
		// 톰캣에 의해 자동 호출 
		// 이 메소드 영역이 JSP 이다 => service
		// JSP => 실행시 class로 변경 => 컴파일해서 실행
		// 전송타입 => html,xml,json
		response.setContentType("text/html;charset=UTF-8");
		//      text/xml , text/plain
		// HTML 전송 
		PrintWriter out=response.getWriter();
		//              -------------------- 접속한 클라이언트 브라우저
		loseAniDAO dao=loseAniDAO.newInstance();
		List<loseAniVO> list=dao.loseDogListData(1);
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>실종 강아지 목록</h1>");
		out.println("<table border=1 width=800>");
		out.println("<tr>");
		out.println("<th width=15%></th>");
		out.println("<th width=20%>사진</th>");
		out.println("<th width=15%>제목</th>");
		out.println("<th width=40%>내용</th>");
		out.println("</tr>");
		for(loseAniVO vo:list)
		{
			out.println("<tr>");
			out.println("<td width=10%>"+vo.getLdno()+"</td>");
			out.println("<td width=30%><img src="+vo.getImage()+" width=30 height=30></td>");
			out.println("<td width=10%>"+vo.getTitle()+"</td>");
			out.println("<td width=10%>"+vo.getInfo()+"</td>");
//			out.println("<td width=10%>"+vo.getLoseinfo()+"</td>");
//			out.println("<td width=10%>"+vo.getLosedate()+"</td>");
//			out.println("<td width=10%>"+vo.getLoseloc()+"</td>");
//			out.println("<td width=10%>"+vo.getFeature()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}
