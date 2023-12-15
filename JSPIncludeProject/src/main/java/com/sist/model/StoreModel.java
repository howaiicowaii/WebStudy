package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import java.util.*;
import com.sist.vo.*;
/*
	DAO => 오라클 연결
	Model => 요청을 받아서 데이터베이스 연동해서 처리하고 결과값을 모아서 request에 저장
	Controller => Model에서 모아둔 request를 JSP에게 전송
*/
public class StoreModel {
	@RequestMapping("store/all.do")
	public String store_all(HttpServletRequest request,HttpServletResponse response)
	{
		// all.jsp 로 데이터베이스 결과값을 전송 (request에 실어서)
		String page=request.getParameter("page");
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		// DB 연동
		GoodsDAO dao=GoodsDAO.newInstance(); // 싱글턴 메모리 생성
		List<GoodsVO> list=dao.goodsAllListData(curpage);
		int totalpage=dao.goodsAllTotalPage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		// => 쿠키 데이터를 전송 (목록 밑에 출력해야하니깐)
		Cookie[] cookies=request.getCookies();
		List<GoodsVO> cList=new ArrayList<GoodsVO>();
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
				if(cookies[i].getName().startsWith("goods_"))
				{
					String no=cookies[i].getValue();
					GoodsVO vo=dao.goodsCookieData(Integer.parseInt(no));
					cList.add(vo);
				}
			}
		}
		request.setAttribute("cList", cList);
		request.setAttribute("count", cList.size());
		// 요청할 때 => .do
		// include할 때 => .jsp
		request.setAttribute("store_jsp", "../store/all.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("store/best.do")
	public String store_best(HttpServletRequest request,HttpServletResponse response)
	{
		
		request.setAttribute("store_jsp", "../store/best.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("store/sp.do")
	public String store_sp(HttpServletRequest request,HttpServletResponse response)
	{
		request.setAttribute("store_jsp", "../store/sp.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("store/new.do")
	public String store_new(HttpServletRequest request,HttpServletResponse response)
	{
		
		request.setAttribute("store_jsp", "../store/new.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
	@RequestMapping("store/detail_before.do")
	public String store_detail_before(HttpServletRequest request, HttpServletResponse response)
	{
		String no=request.getParameter("no");
		Cookie cookie=new Cookie("goods_"+no, no);
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24); // 하루 동안 쿠키 저장
		// 브라우저로 전송 (response 는 한번밖에 못써서 따로 만들어서 나눠야 한다)
		response.addCookie(cookie);
		return "redirect:../store/detail.do?no="+no; // request가 필요가 없으니 redirect
		// sendRedirect는 반드시 .do 가 있어야 실행이 된다
		// ㅁㅁ_ok / ㅁㅁ_before 는 다 redirect로 보낸다
		// Cookie 는 response 가 보낸다
	}
	@RequestMapping("store/detail.do")
	public String store_detail(HttpServletRequest request,HttpServletResponse response)
	{
		// 사용자가 보내준 데이터 no 받기 (GoodsDAO 의 detail 메소드에서)
		String no=request.getParameter("no");
		GoodsDAO dao=GoodsDAO.newInstance();
		GoodsVO vo=dao.goodsAllDetailData(Integer.parseInt(no));
		String price=vo.getGoods_price();
		// 30,000원 이렇게 저장되어 있는데, 30000 이렇게 바꿔서 결제 코드 amount 뒤에 넣어야 한다 (ok.jsp 에 있음)
		price=price.replaceAll("[^0-9]", ""); // 숫자를 제외하고 다 공백으로 바꿔라 ( [0-9] = 숫자 )
		vo.setPrice(price); // 자른 값 다시 담아놨다
		request.setAttribute("vo", vo);
		// [가-힣] = 한글 전체 / [^가-힣] = 한글을 제외한 / [^A-Za-z] = 영문을 제외한
		// ${store_jsp} => request.getAttribute("store_jsp")
		request.setAttribute("store_jsp", "../store/detail.jsp");
		request.setAttribute("main_jsp", "../store/store_main.jsp");
		return "../main/main.jsp";
	}
}
