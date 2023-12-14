package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/*
XML 파싱
Annotation => 클래스 찾기 => 메소드 찾기
=> MVC동작
   1. 요청을 한다 (jsp파일에서!) : 보통 <a>,<form> 에서 요청함
      => DispatcherServlet(Controller) => 이미 제작되어있음
      => @WebServlet("*.do")
             |       ====== list.do, insert.do
             |              ----     ------
             |              우리는 이 구분문자를 잘라내서 사용함
             |서버에서 받을수 있는 부분
              URI, URL => 서버연결주소
              => URI를 이용해서 모델을 찾음
   2. DispatcherServlet (Controller)
      역할
      ===요청을 받는다 (브라우저 => 요청을 받는 파일
                             => JSP / Servlet)
                    JSP =====> View(화면출력)
                    Servlet ==> 연결(자바/HTML)
                                ============
      ===분리된 Java를 찾는다 ===> 연결
                         request/session
   3. MVC 목적
      1) 보안 (JSP => 배포 (소스를 통으로 전송)) => 유지보수
      2) 여러명이 동시개발을 할수 있게 만들어주는게 백/프론트 구분
      3) JSP의 단점 : 확장성, 재사용, 변경 이 안됨
         ========= 사이트 제작시 버린다
      4) 신규사원 확장
      => MVVM / MVVP
   4. 소프트웨어 => 회귀
   ==================
   
   5. 동작 
                 request
   JSP (링크,버튼) ======= DispatcherServlet
                              ==> Model (DAO와 연동)
                                  =====
                                  request에 결과값 담기
                                  => setAttribute() (= 데이터를 보내는구나)
              DispatcherServlet <==
               => request가 필요하다 => request를 JSP로 전송
                     JSP로 request를 전송 메소드
                     => forward(request,response)
                     => SELECT => forward()
               => request가 필요없다 => 화면만 이동
                     화면만 변경하는 메소드
                     => sendRedirect(파일명)
                     => 회원가입 , 로그인 , 글쓰기 
                     => INSERT,UPDATE,DELETE => sendRedirect()
      => DispatcherServlet 은 수정하면 안된다. 고정한다 => .jar
      
      1. 설정 파일 ( 어떤 프레임워크를 써도 설정파일이 따로 있다 )
         Spring : application-context.xml
                  application-datasource.xml
                  application-security.xml
                  => 태그, 속성을 Spring에서 제공하는 것만 사용이 가능
                  => dtd ( 태그 얘네만 써야하고~ 속성은 ~이렇다 => dtd (제약조건) )
                  
*/
import java.util.*;
import java.net.*;
@WebServlet("*.do")
// => 
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> clsList=
				new ArrayList<String>(); // ArrayList 안에 class 저장
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 1. XML의 경로 읽기 => XML안에 클래스 등록
		try
		{
			URL url=this.getClass().getClassLoader().getResource("."); // 현재 이 폴더의 경로 => .
			File file=new File(url.toURI());
			System.out.println(file.getPath());
			String path=file.getPath();
			path=path.replace("\\", File.separator);
			// Window => \\ , Mac => /  : 경로명 자동 변경
			path=path.substring(0,path.lastIndexOf(File.separator));
			// C:\WebDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMVCFinalProject\WEB-INF\classes
			// 뒤 '/classes'를 잘라줘야 WEB-INF 에서 xml 읽어올 수 있다
			System.out.println(path);
			path=path+File.separator+"application.xml"; // application.xml => Model 클래스 등록하는 XML
			
			// XML파싱 => XML안에서 필요한 데이터를 추출
			// 1. 파서기 생성
			DocumentBuilderFactory dbf=  // 파서기 생성 => 팩토리
					DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder(); // 파서기
			// 2. XML을 전송
			Document doc=db.parse(new File(path)); // XML에 담긴 데이터 파서해서 전송
			// 3. root 태그 읽기 => 테이블
			Element root=doc.getDocumentElement();
			// beans ==> FROM 테이블명 / beans=>table / bean=>row / id,class=>column
			// 4. root 밑에 있는 태그를 모아서 데이터 추출
			NodeList list=root.getElementsByTagName("bean"); // NodeList => 모아준다
			// 1,2,3,4,5,6,7,8,9,10 을 10번을 쓸건지, for문 활용할건지 => NodeList list
			// list => <bean>(4개 담겨있다. application.xml 에 있는)
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i); // bean태그 하나 읽었다 => list.getLength() 로 필요한 만큼 반복
				String id=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println(id+":"+cls);
				
				clsList.add(cls);
			}
		}catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI(); // 사용자 요청값 먼저 받기
		// http://localhost/JSPMVCFinalProject/board/list.do
		// /JSPMVCFinalProject/board/list.do => URI (전체가 URL)
		// /JSPMVCFinalProject => ContextPath => /board/list.do
		// /board/list.do => +1 => board/list.do 
		uri=uri.substring(request.getContextPath().length()+1);
		try
		{
			// RequestMapping 찾는
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls); // 정보를 읽어온다
				// 클래스 정보 읽기 (=> clsName )
				/*
					메소드 / 멤버변수 / 생성자 확인
				*/
				Object obj=clsName.
						getDeclaredConstructor().newInstance(); // 메모리 할당
				// 클래스 정보를 이용해서 메모리 할당
				// => 리플렉션
				Method[] methods=clsName.getDeclaredMethods();
				//               클래스에 선언된 모든 메소드를 가지고 온다
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					// 메소드 위에 선언된 @RequestMapping을 가지고 온다
					// => m(메소드) => 위에 저장되어 있는 어노테이션을 가지고 온다
					// 저장된 객체
					/*
						A a=new A() => a
						A a=a.getClass()
						A a=A.class
						
						public void aaa(int a,int b)
						
						aaa(10)
					*/
					if(rm.value().equals(uri))
					{
						// m.invoke = model.execute(request) 
						String jsp=(String)m.invoke(obj, request,response); // 형변환 필수
						// m이라는 메소드 호출한다 => invoke(객체,매개변수...) => 매개변수 무조건 2개씩 request,response
						if(jsp==null) // void => ajax
						{
							return;
						}
						else if(jsp.startsWith("redirect:"))
						{
							// return "redirect:list.do" 
							jsp=jsp.substring(jsp.indexOf(":")+1);
							response.sendRedirect(jsp);
						}
						else
						{
							RequestDispatcher rd=
									request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
				}
			}
		}catch(Exception ex) {}
	}

}
