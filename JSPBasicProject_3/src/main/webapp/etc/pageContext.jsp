<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. 내장 객체 얻기 
	   getRequest() , getResponse() , getOut()
	   getSession() , getPage() , getException()
	   application => getServletContext()
	   => 사용 빈도가 거의 없다 (99.99%)
	   
	   request.getParameter()
	   pageContext.getRequest().getParameter()
	2. 웹 흐름 제어
	   include() , ***forward() => 파일마다 request 공유한다
	   pageContext.include() ==> 사용 거의 X
	    => <jsp:include> ==> 이렇게 사용 
	    => 
	       1. <%@ include file=""%> : 정적
	          => file에는 반드시 file명을 설정한다
	             menu / footer
	       2. <jsp:include page=""> : 동적 (이렇게 사용할 예정)
	          => 내용출력시에 변경
	             page=변수명
	             
	       => JSP안에 특정 위치에 다른 JSP를 포함 (= include)
	       a.jsp
	        <html>
	          <body>
	            <%
	                int a=10;
	            %>
	            <h1><%= a%></h1>
	          </body>
	        </html>
	        =====================
	        <html>
	        <body>
	          <h1>10</h1>
	        </body>
	        </html>
	        =====================
	        
	       b.jsp
	        <html>
	          <body>
	            <%
	                 int a=100;
	            %>
	            <h1><%=a%></h1>
	            =========================
	            <%@ include file="a.jsp"%> => 한꺼번에 컴파일해서 변수 겹쳐서 오류 발생
		            ---<html>
		               <body>
		               <%
		                int a=10;
		               %>
		                <h1><%= a%></h1> ==> a(변수)가 겹치서 오류 발생
		               </body>
		            ---</html>
		            =========================
		            <jsp:include page="a.jsp"> 
		            => a 따로 b.jsp 따로 컴파일하고 HTML을 붙여서 변수가 같아도 오류 발생없다
	          </body>
	        </html>
	        =====================
	        <html>
	        <body>
	          <h1>100</h1>
	        </body>
	        </html>
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>