<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<jsp:useBean id="model" class="com.sist.model.dogModel"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	model.dogListData(request);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 1024px;
}
.title{
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis; 
}
</style>
</head>
<body>
<div class="row">
<h1 class="text-center">실종 강아지</h1>
<c:forEach var="vo" items="${list}" >
<div class="col-md-3">
   <div class="thumbnail">
    <a href="#">
     <img src="${vo.image }" title="${vo.title} ">
     <div class="caption">
          <p class="title">${vo.title }</p>
        </div>
    </a>
    </div>
   </div>
  </c:forEach>
</div>
<div style="height: 10px"></div>
<div class="row">
 <div class="text-center">
  <ul class="pagination">
  	<c:if test="${startPage>1 }">
  	 <li><a href="dog.jsp?page=${startPage-1 }">&lt;</a></li>
  	</c:if>
  	<c:forEach var="i" begin="${startPage }" end="${endPage }">
  	 <li ${curpage==i?"class=active":"" }><a href="dog.jsp?page=${i}">${i}</a></li>
  	</c:forEach>
  	<c:if test="${endPage<totalpage }">
  	 <li><a href="dog.jsp?page=${endPage+1 }">&gt;</a></li>
  	</c:if>
  </ul>
 </div>
</div>
</body>
</html>