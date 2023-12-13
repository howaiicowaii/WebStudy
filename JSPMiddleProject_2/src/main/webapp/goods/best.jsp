<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="com.sist.model.GoodsModel"/>
<%
	// Controller가 이 부분을 호출하는 것으로 변경될 것.
	model.goodsListData(request);
    // request 를 보내주고 출력에 필요한 데이터를 받아온다 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="row">
   <h1 class="text-center">베스트상품</h1>
<c:forEach var="vo" items="${list}" >
 <div class="col-md-3">
    <div class="thumbnail">
      <a href="#">
        <img src="${vo.goods_poster}" title="${goods_name }" >
        <div class="caption">
          <p>${vo.goods_price }</p>
        </div>
       </a>
      </div>
   </div>
</c:forEach>
<div style="height: 10px"></div>
 <div class="row">
  <div class="text-center">
	<ul class="pagination">
	  <%--
	    <!ENTITY lt "<">
	    <!ENTITY gt ">">
	    => &lt;
	   --%>
	  <c:if test="${startPage>1 }">
	   <li><a href="main.jsp?type=${type}&page=${startPage-1}">&lt;</a></li>
	  </c:if>
	  <c:forEach var="i" begin="${startPage }" end="${endPage }">
	   <li ${curpage==i?"class=active":"" }><a href="main.jsp?type=${type}&page=${i}">${i }</a></li>
	  </c:forEach>
	  <c:if test="${endPage<totalpage }">
	   <li><a href="main.jsp?type=${type}&page=${endPage+1}">&gt;</a></li>
	  </c:if>
	</ul>
  </div>
  </div>
</body>
</html>