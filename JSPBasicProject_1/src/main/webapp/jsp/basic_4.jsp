<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.sist.dao.*" %>
<%--
	1. 데이터 읽기 
	   1) import를 설정
	      <%@ import="java.util.*,com.sist.dao.*"%>
	      
	      <%@ import="java.util.*"%>
	      <%@ import="com.sist.dao.*"%>
	2. HTML의 해당위치에 <% %> => for
	   => 데이터 출력은 <%= %>
	   => 중심이 View (화면 출력)
 --%>
<%
	EmpDAO dao=EmpDAO.newInstance();
	List<EmpVO> list=dao.empListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/table.css">
<style type="text/css">
	.container{
		margin-top: 50px;
		width: 100%;
	}
	h1{
		text-align: center;
	}
	.row,.table_content{
		width: 800x;
		margin: 0px auto; /* 가운데 정렬 */
	}
</style>
</head>
<body>
 <div class="container">
  <div class="row">
  <h1>사원 목록</h1>
  <table class="table_content" width=800>
    <tr>
      <th>사번</th>
      <th>이름</th>
      <th>직위</th>
      <th>입사일</th>
      <th>급여</th>
    </tr>
    <%--
    	<% 자바 소스 %> : 메소드안에 들어가는 자바소스
    	<%= 출력물 %> : out.println(=====)
    	                             | 첨부 => ; 을 사용하면 오류 발생
    	= 자바 / HTML을 구분
    	= 모든 소스는 _jspService()에 첨부된다 => 톰캣에 의해 자동 생성 처리
    	   톰캣 : WAS => JSP/Servlet 엔진
    	         형상관리 : Git
     --%>
    <% 
    	for(EmpVO vo:list)
    	{
    %>
    	  <tr class="dataTr">
    	    <td class="text-center"><%=vo.getEmpno() %></td>
    	    <td class="text-center"><%=vo.getEname() %></td>
    	    <td class="text-center"><%=vo.getJob() %></td>
    	    <td class="text-center"><%=vo.getHiredate().toString() %></td>
    	    <td class="text-center"><%=vo.getSal() %></td>
    	  </tr>
    <%
    	}
    %>
  </table>
  </div>
  </div>
</body>
</html>