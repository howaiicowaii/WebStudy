<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	JSTL (Java Standard Tag Library)
	  -- Tag Lib
	  <% %> 를 태그형으로 제작
	    =======================================================
	    = 1. 변수 선언 => int a=10; => <c:set var="a" value="10"/>
	      2. 제어문 => 
	          for(int i=1;i<=10;i++)
	          => <c:forEach var="i" begin="1" end="10" step="1"/>
	          for(SawonVO vo:list)
	          => <c:forEach var="vo" items="list" varStatus="in">
	                    (varStatus : 인덱스 번호)   ========== index
	                                 => 번호 출력 / 다른 List
	                                 
	          <c:forTokens var="" value="" delim="">  (delim : 구분자)
	          => StringTokenizer st=
	          		new StringTokenizer(value,delim)
	          	 while(st.hasMoreTokens())
	          	 {
	          	 	
	          	 }
	          => <c:if> 조건문
	             <c:if test="">
	                   ======== 조건문
	             => if(test)
	             => 단점 => else 문장이 없다
	                       ============== 사용자 정의 (회사마다 다르다)
	          => 다중 조건문 => XML로 제작
	                 XML
	                 ===
	                  1. 태그나 속성 => 대소문자 구분
	                  2. 속성값 => "" 필수
	                  3. 계층구조 => 여는 태그 / 닫는 태그 일치
	                     => <jsp:include>
	             <c:choose>
	               <c:when test="조건문">출력</c:when>
	               <c:when test="조건문">출력</c:when>
	               <c:when test="조건문">출력</c:when>
	               <c:otherwise>default</c:otherwise>
	             <c:/choose>
	      3. 화면 이동
	             <c:redirect url=""/>
	             => response.sendRedirect(url)
	      6. 화면 출력
	             <%= %>
	             <c:out value=""/> => Jquery => $
	              ${}  => import가 동일하게 있으면 오류 발생(Jquery의 단점) 
	      =========================== core
	      4. 날짜 변환 / 숫자 변환 ====== format
	         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	         <fmt:formatDate value="" pattern="yyyy-MM-dd">
	         => 오라클에서 => TO_CHAR(regdate,'YYYY-MM-DD')
	         DecimalFormat d=new DecimalFormat("###,###");
	         <fmt:formatNumber value="" type="currency">
	      5. 메소드 호출 ============== functions
	      6. 화면 출력
 --%>
<%
	// JSTL => 출력은 EL ( <% 사용하지 않는다 )
	List<String> nList=new ArrayList<String>();
	nList.add("홍길동");
	nList.add("이순신");
	nList.add("강감찬");
	nList.add("심청이");
	nList.add("춘향이");
	
	List<String> sList=new ArrayList<String>();
	sList.add("남자");
	sList.add("남자");
	sList.add("남자");
	sList.add("여자");
	sList.add("여자");
	
	request.setAttribute("nList", nList);

%>
<c:set var="sList" value="<%=sList %>"/>
<!-- 일반 변수가 아니라 request.setAttribute(var,value) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>이름</h1>
  <ul>
    <%--for(String name:nList) --%>
    <c:forEach var="name" items="${nList }">
      <li>${name }</li>
    </c:forEach>
  </ul>
  <h1>성별</h1>
  <ul>
    <c:forEach var="sex" items="${sList }">
      <li>${sex }</li>
    </c:forEach>
  </ul>
  <h1>이름(성별)</h1>
  <ul>
    <c:forEach var="name" items="${nList }" varStatus="s"> <!-- varStatus = 인덱스 -->
	  <li>${s.index+1}.${name }(${sList[s.index] })</li>
    </c:forEach>
  </ul>
</body>
</html>