<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="model" class="com.sist.dao.EmpModel"/>
<%
	model.empListData(request); // request에 값을 실어달라. 는 뜻 실어서 Model로 보낸다
	// => Controller 가 담당해서 호출해줄 것
	// => <% => 자바 코딩을 완전히 제거 => HTML(ThymeLeaf) => HTML에서 루프 돌리는 것 => JSP 필요성 점점 사라진다
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <table width=800 border=1>
    <tr>
     <th>사번</th>
     <th>이름</th>
     <th>직위</th>
     <th>입사일</th>
     <th>급여</th>
    </tr>
    <%--
    	for(EmpVO vo:request.getAttribute("list")) -> 이거 줄여서 아래처럼 사용
     --%>
    <c:forEach var="vo" items="${list }">
      <tr>
        <td>${vo.empno }</td>
        <td>${vo.ename }</td>
        <td>${vo.job }</td>
        <td>${vo.hiredate }</td>
        <td>${sal }</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>