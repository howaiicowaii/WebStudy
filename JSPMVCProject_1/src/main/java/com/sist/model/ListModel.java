package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class ListModel {
	/*
		MVC
		Model => java => 요청처리
		View => jsp => 요청 처리 결과를 받아서 화면에 출력만
		        ------------------------------------
		        자바 사용하지 않는다. => ${} , JSTL
		        =============== <% %> 사라진다
	    Controller => servlet 
	            jsp에서 요청을 받아서
	            => 처리에 해당되는 Model을 찾아주는 역할 수행
	            => Model을 찾아서 메소드를 호출 => request를 jsp로 연결 
	    ========================================================
	     (Controller 를 Dispatcher라고 하기도 한다)
	     Dispatcher : 배달부 => Framework : 이미 제작됨
	     => DispatcherServlet
	     => FilterDispatcher
	     => Controller => 서빙
	        1) 주문을 받는다 => JSP에게 요청을 받는다 
	        2) 주방을 거친다 => Model을 찾는다
	        3) 음식을 배달 => request를 통해서 한번에 전달
	                요청(request)(=주문)
	                요청자의 ip                     request
	     사용자(JSP) ============== Controller =============== 주방 (패키지)
	     => <a>,<form>                                      종류별 요리
	                                                       =========
	                                                        Model
	                                                        => 처리
	                               Controller <==============
	                                   |      결과물을 주문서와 동시에 보내준다
	                                   |       ==========================
	                                   |       request.setAttribure()
	                                request를 JSP로 전송
	                                ==================
	                                출력 => 브라우저에서 읽어간다 
	     
	     
	*/
	// 요청을 받아서 처리한 결과값 전송 
	public String execute(HttpServletRequest request)
	{
		request.setAttribute("msg", "게시판 목록");
		return "list.jsp"; // request를 받아서 출력하는 위치
	}
}
