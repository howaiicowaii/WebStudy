<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <!-- Contact Form Begin -->
    <div class="contact-form spad">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="contact__form__title">
              <h2>장례 절차</h2>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 사이드 예약 매뉴 시작 -->
    <div class="res_submenu">
      <a href="reserve.do">
       <i class="xi-calendar-add"></i>
       <p>예약하기</p>
      </a>
    </div>
    <!-- 사이드 예약 매뉴 종료 -->
    
    <!-- 장례절차 반복 시작 -->
    <!-- 장례절차 1번만 사진때문에 빼둠 -->
    <div class="text-center funeral_produce_all container">
        <img src="../img/pro1.jpg" />
        <div class="funeral_produce_text">
          <span>1</span>
          <h3>장례예약</h3>
          <p>아이가 무지개 다리를 건너면 대표번호(1688-1240) 또는 온라인 예약을 통해 원하는 일시에 장례를 예약합니다.</p>
        </div>
      </div>
  <c:forEach var="fdvo" items="${list }">
    <div class="text-center funeral_produce_all container">
        <img src="${fdvo.infoimage }" />
        <div class="funeral_produce_text">
          <span>${fdvo.mno }</span>
          <h3>${fdvo.infoh3 }</h3>
          <p>${fdvo.infop }</p>
        </div>
      </div>
   </c:forEach>
   <!-- 장례절차 반복 종료 -->
   
    <!-- Contact Form End -->
   
</body>
</html>