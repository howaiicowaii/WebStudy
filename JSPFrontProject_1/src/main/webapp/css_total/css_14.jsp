<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	* 공부해야할 것들
	1. 선택자 => JavaScript 에서도 사용 
	2. 글자 관련 
	3. margin / padding / border
	4. background
	5. position
	    => fixed
	    => relative
	    => 배치 => top/bottom/left/right
	6. overflow
	===================================
	CSS는 약간의 수정
	=> 퍼블리셔
	
	HTML / CSS => 화면 UI
	JavaScript => UI 제어
	Java => 데이터 관리
	        오라클 ==> 자바 ==> HTML/JavaScript
	오라클 => 데이터 저장 장소
	============== 통합 (Spring)
	Spring-Boot <==> Front
	=========== MSA
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}
body {
	width: 960px;
	margin: 0px auto;
	overflow: hidden;
}
#nav{
	width: 300px;
	float: left;
}
#section{
	width: 660px;
	float: right;
}
li {
	list-style: none;
}
@media screen and (max-width:768px){
	body{width:auto}
	#nav{width:auto;float: none}
	#section{width:auto;float: none}
}
</style>
</head>
<body>
  <div id="nav">
    <ul>
     <li>홈</li>
     <li>뉴스 1</li>
     <li>뉴스 2</li>
    </ul>
  </div>
  <div id="section">
  서울 아파트 전세 시장이 하반기부터 반등세로 돌아선 뒤 고공행진을 지속하고 있다. 
  특히, 내년 서울 아파트 입주 물량이 20년 만에 역대 최저 수준일 것으로 예상되면서 
  내년부터 본격적으로 전세대란이 나타날 수 있다는 전망이 나온다.

	18일 KB부동산의 주택가격 통계에 따르면 지난달 서울 아파트 3.3㎡당 전세 평균가격은 
	2308만5000원으로, 전월 대비 0.88% 상승한 것으로 집계됐다. 
	3.3m²당 전세 가격이 2300만 원을 넘은 것은 올해 2월 이후 처음이다. 
	서울 3.3m²당 전셋값은 1월 2398만3000원에서 7월 2245만1000원까지 하락했지만, 
	8월부터 다시 상승세로 돌아선 뒤 29주 연속 상승세다.
  </div>
</body>
</html>