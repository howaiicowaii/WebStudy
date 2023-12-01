package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.ReplyDAO;


@WebServlet("/ReplyDeleteServlet")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// rno,type,no 받아놔야 한다
		String rno=request.getParameter("rno");
		String type=request.getParameter("type");
		String no=request.getParameter("no");
		// DAO 연동 
		ReplyDAO dao=ReplyDAO.newInstance();
		dao.replyDelete(Integer.parseInt(rno)); 
		
		// 화면 이동 
		response.sendRedirect("MainServlet?mode=5&no="+no+"&type="+type);
		// 삭제 버튼 눌러서 댓글 삭제한 후 해당 화면 다시 출력해서 삭제한 거 사라진 걸 볼 수 있다
		
	}

}



