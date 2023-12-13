package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
/*
	1. 관련된 클래스가 여러개 있다 => 인터페이스로 통합
	2. 조건문 없이 사용하려면 Map 이용해서 처리
	3. 미리 클래스 메모리 할당 => 주소만 찾을 수 있게 프로그램 짠다
	   ================== 싱글턴 (메모리가 1개만 생기기 때문에)
	4. Model을 찾는 경우에는 URL주소를 이용해서 찾는다
	5. 구분
	   ===
	   request를 JSP로 전송 ==> forward (request초기화 안되고 request,response 다 넘어감)
	   request를 초기화하고 JSP만 호출 ==> redirect (sendRedirect)
	   JSP를 변경하지 않고 전송 (데이터만 전송) => Ajax (리턴형이 void)
	    화면에 보여준다 (forward) (request 유지)
	    화면에 없이 기존 화면으로 돌아가야 한다 (sendRedirect) (request 초기화)
	    화면자체에서 움직이지 않고 데이터만 들어오는 (Ajax) (void 사용) (로그인창)
	                                       (void => JavaScript 연동)
	    
	=> Controller
	   1. 사용자로부터 요청을 받는다
	   2. Model을 찾는다
	   3. Model의 메소드 호출 => request 에 필요한 값 담는다
	   4. request를 JSP로 전송
	=> Model
	   1. 요청값을 받는다 => 매개변수에 (request)
	   2. 요청 처리 => DAO
	   3. 결과값을 request에 담는다 => setAttribute()
	=> View
	   1. Controller에서 보내준 request를 출력
*/
@WebServlet("*.do")
// list.do (목록 찾기) / update.do / delete.do / insert.do
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		clsMap.put("list", new ListModel());
		clsMap.put("insert", new InsertModel());
		clsMap.put("update", new UpdateModel());
		clsMap.put("delete", new DeleteModel());
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd=request.getRequestURI();
		/*
			/JSPMVCProject_2/list.do
			================
			 ContextPath
		*/
		System.out.println("cmd=>1:"+cmd);
		cmd=cmd.substring(request.getContextPath().length()+1,
				cmd.lastIndexOf("."));
		System.out.println("cmd=>2:"+cmd);
		
		Model model=(Model)clsMap.get(cmd);
		System.out.println("model:"+model);
		String jsp=model.execute(request);
		
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}

}




