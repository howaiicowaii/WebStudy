<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트 반복문
	= do~while : 사용빈도가 거의 없다
	             조건이 후조건을 사용한다 => 무조건 한번이상은 수행
	  형식)
	      초기값
	      do
	      {
	         반복수행문장
	         증가식
	      }while(조건문)
	= while : 무한루프(게임,네트워크 통신) => 선조건 => 수행이 안될 수 있
	  형식) 
	      초기값 
	      while(조건) ==> 조건이 false일때 까지 수행
	      {
	         반복수행문장
	         증가식
	      }
	      ==> 반복횟수가 지정이 안된 상태에서 많이 사용
	= for : 반복횟수가 지정된 경우 (자바스크립트에서 반복문의 핵심)
	  => 웹 : 사용자에게 보여주는 역할
	         ====================
	         테이블 : 게시판 => 한눈에 볼 수 있는 갯수 (15~20개)
	         div : card => 이미지 => 10~15개
	         ====== 페이징
	         ====== 프로그램은 본인이 아닌 사용자에게 맞춰서 편하게 제작
	  => 형식)
	      for(초기값;조건문;증가식)
	      {
	         반복 수행문장
	      }
	  for in / for of / ***forEach / ***map
	      
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// type="text/javascript" ES5, ES6 => text/babel
window.onload=function(){
	// do~while
	document.write("<h3>do~while</h3>")
	let i=1; // 1
	do{
		document.write("i="+i+"<br>"); // 2
		i++; // 3
	}while(i<=10) // 4 ==> 2번으로 이동해서 반복. 4번 false면 종료
	document.write("<hr>")
	// while
	document.write("<h3>while</h3>")
	i=1; // 초기값  == 1
	while(i<=10) // == 2
	{
		document.write("i="+i+"<br>") // 3
		i++; // 4 ==> 조건으로 이동. 2번 false되면 종료
	}
	document.write("<hr>")
	document.write("<h3>for</h3>")
	//   1    2    4
	for(i=1;i<=10;i++)
	{
		document.write("i="+i+"<br>") // 3
	}
	document.write("<hr>")
	
	document.write("<h3>break</h3>")
	for(i=1;i<=10;i++)
	{
		if(i==5) break;
		document.write("i="+i+"<br>")
	}
	document.write("<hr>")
	
	document.write("<h3>continue</h3>")
	// 특정 부분을 제외할 경우에 주로 사용
	// 제외하고 증가식으로 이동
	for(i=1;i<=10;i++)
	{
		if(i==5) continue // i==5일때 => 5는 처리 안하고 증가식으로 이동
		document.write("i="+i+"<br>")
	}
	document.write("<hr>")
}
</script>
</head>
<body>

</body>
</html>