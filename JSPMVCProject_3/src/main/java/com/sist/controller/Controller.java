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
import javax.xml.parsers.*;
import org.w3c.dom.*;
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String path="C:\\WebDev\\webStudy\\JSPMVCProject_3\\src\\main\\webapp\\WEB-INF\\application.xml";
		try
		{
			DocumentBuilderFactory dbf=
					DocumentBuilderFactory.newInstance();
			// HTML , XML , WML , HDML... 
			DocumentBuilder db=dbf.newDocumentBuilder();
			// 파서기
			Document doc=db.parse(new File(path)); // 문서를 다 읽어놓고 시작 like Jsoup
			// 1. 루트 (beans) => 테이블명
			Element beans=doc.getDocumentElement();
			//System.out.println(beans.getTagName());
			// 2. 같은 태그를 묶어서 제어 
			NodeList list=beans.getElementsByTagName("bean"); // bean 태그 한번에 묶음
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i); // bean 태그 하나씩 읽어오기
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println(id+"=>"+cls);
				// 메모리 할당 => Map에 저장
				Class clsName=Class.forName(cls);
				Object obj=clsName.getDeclaredConstructor().newInstance();
				System.out.println(obj);
				clsMap.put(id, obj);
			}
		}catch(Exception ex) {}
	}
	// ==> 메뉴 제작 (init)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 주문 받기 시작 (service) => list.do
		String cmd=request.getRequestURI();
		cmd=cmd.substring(request.getContextPath().length()+1,
				cmd.lastIndexOf("."));
		Model model=(Model)clsMap.get(cmd);
		String jsp=model.handelRequest(request);
		if(jsp.startsWith("redirect:"))
		{
			jsp=jsp.substring(jsp.indexOf(":")+1);
			response.sendRedirect(jsp);
		}
		else // sendRedirect 냐 forward 냐
		{
			RequestDispatcher rd=
					request.getRequestDispatcher(jsp);
			rd.forward(request, response);
		}
	}

}
