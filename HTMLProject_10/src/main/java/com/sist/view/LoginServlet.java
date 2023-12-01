package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import com.sist.dao.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.write("<!DOCTYPE html>"); // HTML5 버전을 쓰겠다는 문장 => 유효성 검사하기위해
		out.write("<html>");
		out.write("<head>");
		out.write("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.write("<style>");
		out.write(".container{margin-top:50px}");
		out.write(".row{margin:0px auto;width:300px");
		out.write("h3{text-align:center}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<div class=container>");
		out.write("<div class=row>");
		out.write("<form method=post action=LoginServlet>");
		out.write("<table class=table>");
		out.write("<tr>");
		out.write("<th width=20% class=\"text-center danger\">ID</th>");
		out.write("<td width=80%>");
		out.write("<input type=text name=id size=15 required>");
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<th width=20% class=\"text-center danger\">PWD</th>");
		out.write("<td width=80%>");
		out.write("<input type=password name=pwd size=15 required>"); // 입력 요청메세지 
		out.write("</td>");
		out.write("</tr>");
		
		out.write("<tr>");
		out.write("<td colspan=2 class=text-center>");
		out.write("<input type=submit value=로그인 class=\"btn btn-sm btn-primary\">");
		out.write("</td>");
		out.write("</tr>");

		out.write("</table>");
		out.write("</div>");
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 처리용 => login 처리 , 회원가입 처리... / 모양 잡는 건 다 GET 방식
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		// 1. 전송값을 받는다  
		// 사용자가 보낸 모든 값이 request에 들어있다. / response : 톰캣에 의해 request 정보들을 넘긴다
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd"); // <input type=password name=pwd << 이거랑 이름 같아야 한다
		// 2. DAO 연동 
		MemberDAO dao=MemberDAO.newInstance();
		MemberVO vo=dao.memberLogin(id, pwd);
		if(vo.getMsg().equals("OK")) // 로그인 성공시
		{
			// 화면 이동
			HttpSession session=request.getSession();
			// 사용자가 가지고 있다 (브라우저마다 한개씩 생성)
			/*
				session : 서버에 저장
				          => 저장시에 Object로 저장이 가능
				          ** 쿠키는 단점이 문자열만 저장이 가능 
				          ** 장바구니 => session (장바구니는 임시로 저장 Not DB)
			*/
			session.setAttribute("id", id);
			session.setAttribute("name", vo.getName());
			// 세션도 Map방식 (key,값) => 키가 중복되면 안된다
			// 회원수정 => 이름 변경 => session에 다시 저장
			// session.setMaxInactiveInterval(0); // 얼마동안 세션 갖고 갈거냐 
			// 저장 기간의 기본값(default) => 1800초 => 30분
			/*
				1. setAttribute() => 세션에 저장
				2. getAttribute() => 세션에 저장된 값 읽기 
				3. removeAttribute(key) => key만 삭제
				4. invalidate() => 전체 삭제 => 로그아웃 할 때 사용
				5. isNew() => 처음인지 (로그인 2번 못하게)
				6. setMaxinactiveInterval() : 저장 기간 설정
				    => 기본 default = 30분 (초단위 : 1800초)
				7. getId() : session 생성시마다 자동 생성 (숫자형태)
				    => 클라이언트마다 1개씩만 가지고 있다
				    => 이걸 이용하면 채팅 가능
				================================= session의 주요 메소드
			*/
			response.sendRedirect("MainServlet"); // 화면 이동. 로그인 성공시 메인 이동
		}
		// ES5 => ES6 : ; 생략 가능
		else if(vo.getMsg().equals("NOID")) // 아이디 틀린 경우 
		{
			out.write("<script>");
			out.write("alert(\"아이디가 존재하지 않습니다\");");
			out.write("history.back();");
			out.write("</script>");
		}
		else if(vo.getMsg().equals("NOPWD")) // 비밀번호 틀린 경우
		{
			out.write("<script>");
			out.write("alert(\"비밀번호가 틀립니다\");");
			out.write("history.back();");
			out.write("</script>");
		}
		
	}

}
