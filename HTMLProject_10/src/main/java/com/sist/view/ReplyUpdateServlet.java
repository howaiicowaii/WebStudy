package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;

@WebServlet("/ReplyUpdateServlet")
public class ReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// rno, gno, typeno, msg  name=키. 키값들 4개 (GoodsDetailServlet에 있는 코드)
		// 한글 => 디코딩 (인코딩으로 넘어오기 때문에 디코딩 필수)
		try
		{
			// 전송할 때 => 자바 라는 단어를 => %EC%9E%90%EB%B9%94 (= 인코딩)
			// 수신할 때 => %EC%9E%90%EB%B9%94 => 자바 (= 디코딩) 해야 한글 안깨진다
			request.setCharacterEncoding("UTF-8"); // 디코딩. 한글로 다시 원상복귀
		}catch(Exception ex) {}
		String rno=request.getParameter("rno");
		String gno=request.getParameter("gno");
		String tno=request.getParameter("typeno");
		String msg=request.getParameter("msg");
		
		// DAO => 수정한 다음 
		ReplyDAO dao=ReplyDAO.newInstance();
		dao.replyUpdate(Integer.parseInt(rno), msg);
		// 화면 => Detail로 이동
		response.sendRedirect("MainServlet?mode=5&no="+gno+"&type="+tno);
	}

}
