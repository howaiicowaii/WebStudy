<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/>
<%-- 메모리 할당 --%>
<jsp:useBean id="bean" class="com.sist.dao.BoardBean">
  <jsp:setProperty name="bean" property="*"/> <!-- 코드 많이 줄일 수 있다 -->
  <%--
  		bean.setName(request.getParameter("name")) : 아래껄 이렇게 다 자동형변환 해준다 (setProperty)
  		bean.setNo(Integer.parseInt(request.getParameter("no")))
   --%>
</jsp:useBean>
<%
	// id가 등록되면 객체로 사용이 가능 => 컴파일시에 자동으로 자바로 변경이 된다
	dao.boardInsert(bean);
	response.sendRedirect("list.jsp");
%>