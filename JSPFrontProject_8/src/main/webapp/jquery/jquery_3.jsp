<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
	문서 객체(태그) 조작
	1) <태그>(제어하는 문자열)</태그>
	   getter / setter
	   => text() / text("추가문자열")
	   => textContent
 */
$(function(){
	//let h1=$('h1').text()
	//alert(h1)
	//$('h1').text("Jquery값 입력")
	//let div=$('div').text()
	//alert(div)
	//let div=$('div').html(); // innerHTML
	//alert(div)
	
	// $('div').text("<font color=red>Hello Jquery</font>") => text() 는 문자열 그대로 출력
	//$('div').html("<font color=red>Hello Jquery</font>") // => html() 은 font color 적용 
	
	// val() => input 에 value 값 지정할 때 사용 (회원가입할 때 사용)
	let input=$('input').val() // getter (읽는 방법)
	alert(input)
	$('input').val("id가 없습니다") // setter (값 설정) => update 할 때 많이 사용 
	// append , trim , attr , hide show...
	// 나머지 문법은 자바스크립트를 이용한다
})
</script>
</head>
<body>
  <div><h1>Hello Jquery!!</h1></div>
  <input type="text" size=20 value="Hello">
</body>
</html>