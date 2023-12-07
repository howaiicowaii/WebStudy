<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%--
	HTML < Java
	Java에서 오라클 연결 후에 데이터를 읽고 시작
	                     =========== JSP에서 출력 
 --%>
<jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/>
<%--
	BoardDAO dao=new BoardDAO() 와 같은 것. useBean => 메모리 할당. id=객체명
 --%>
<%
	List<BoardBean> list=dao.boardListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../jsp/table.css">
</head>
<body>
  <center>
    <h1>게시판</h1>
    <table class="table_content" width="700">
      <tr>
        <td>
         <a href="insert.jsp">새글</a>
        </td>
      </tr>
    </table>
    <table class="table_content" width="700">
      <tr>
        <th width=10%>번호</th>
        <th width=45%>제목</th>
        <th width=15%>이름</th>
        <th width=20%>작성일</th>
        <th width=10%>조회수</th>
      </tr>
      <%
      	 for(BoardBean vo:list)
      	 {
      %>
       	     <tr class="dataTr">
		        <td widtd=10% class="text-center"><%=vo.getNo() %></td>
		        <td widtd=45%><%=vo.getSubject() %></td>
		        <td widtd=15% class="text-center"><%=vo.getName() %></td>
		        <td widtd=20% class="text-center"><%=vo.getRegdate() %></td>
		        <td widtd=10% class="text-center"><%=vo.getHit() %></td>
		      </tr>
      <%
      	 }
      %>
    </table>
  </center>
</body>
</html>