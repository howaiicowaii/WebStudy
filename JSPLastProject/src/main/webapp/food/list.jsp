<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear"> 
  <h2 class="sectiontitle">전국 맛집리스트</h2>
  <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">
           
          </header>
          <ul class="nospace clear">
           <c:forEach var="vo" items="${list }" varStatus="s">
             <li class="one_quarter ${s.index%4==0?'first':''}"><a href="../food/food_before_detail.do?fno=${vo.fno }"><img src="${vo.poster }" title="${vo.name }"></a></li>
           </c:forEach>
          </ul>
          
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- #######class="current"######################################################################### -->
      <nav class="pagination">
        <ul>
          <c:if test="${startPage>1 }">
           <li><a href="../food/list.do?page=${startPage-1 }">&laquo; Previous</a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
           <li ${i==curpage?"class=current":""}><a href="../food/list.do?page=${i }">${i }</a></li>
          </c:forEach>
          <c:if test="${endPage<totalpage }">
           <li><a href="../food/list.do?page=${endPage+1 }">Next &raquo;</a></li>
          </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <!-- 
      private String poster,name,type,address,phone,theme,
      price,time,seat,content;
      private double score;
     -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>