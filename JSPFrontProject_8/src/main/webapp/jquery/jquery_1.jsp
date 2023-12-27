<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JQuery : 자바스크립트 라이브러리 
	1. 라이브러리 다운로드 / 원격
	   원격
	   <script type="text/javascript"
	           src="http://code.jquery.com/jquery.js">
	   ** jquery가 충돌이 되면 안된다 => main.jsp 에 갖다 놓는 게 좋다
	2. jquery의 시작
	   => window.onload=function()
	   => $(document).ready(function{})
	       ================ 생략 
	      $(function(){}) ==> 한번만 사용
	3. $() => 표준화 (모든 개발자가 동일하게 사용)
	    == selector (CSS)
	    == document.querySelector(CSS)
	    1) 태그 읽어올 때 
	    2) 아이디 읽어올 때
	    3) 클래스 읽어올 때
	    document.querySelector("tr") => $("tr") : Jquery가 태그를 가지고 오는 방식
	    document.querySelector("#aaa") => $("#aaa")
	    document.querySelector(".bbb") => $('.bbb')
	4. $(태그 읽기) => CSS 선택자와 동일
	   $(내장객체) => $(window) , $(document) ... 
	   => Vue 에서는 mounted() , React 에서는 componentDidMount() 였는데,
	                                       ===================
	                                       useEffect() => React-Query
	5. Jquery는 HTML 태그를 제어하는 프로그램
	           ======================== DOMScript
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- import 영역 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ // window.onload=function(){} 쓰는 부분을 jquery는 이렇게 대체
	/*let a=document.querySelector("#a");
	a.style.color="red"
	a.style.backgroundColor="yellow" */
	
	// $('#a').css("color","red").css("backgroundColor","yellow")
	
	$('#a').css({"color":"red","backgroundColor":"green"})
	
	// ** 이 방식이 가장 최신! 가장 간결하다
	// $('.c').css({"color":"yellow","backgroundColor":"black"})
	
	//let c=document.querySelector('.c')	
	// c.style.color="blue" // => .c 는 2개지만 먼저 나오는 .c 만 바뀐다

	let cs=document.querySelectorAll(".c")
	// [] 배열로 인식
	for(let c of cs)
	{
		c.style.color="blue"
	}
	
	$('h1[value=d]').css('color','pink')
	// 속성 => 태그명[속성=값](equals), 태그명[속성*=값](contains)
	// 태그명[속성^=값](startsWith), 태그명[속성$=값](endsWith)
	// h1.addEventListener('click',function(){})
	
	/*
		$('h1').click(function(){})
		$('h1').on('click',function(){})
	*/
	
	$('h1').click(function(){
		alert("Hello Jquery")
	})
})
</script>
</head>
<body>
<!-- id가 중복되면 안된다 -->
<h1 id="a">Hello Jquery</h1>
<h1 class="c">Hello Jquery</h1>
<h1 id="b">Hello Jquery</h1>
<h1 class="c">Hello Jquery</h1>
<h1 value="d">Hello Jquery</h1>
</body>
</html>