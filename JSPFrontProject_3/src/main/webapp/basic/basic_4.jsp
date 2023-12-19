<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	비교연산자
	=== (==) 같다 
	!== (!=) 같지 않다
	<  작다
	>  크다 
	<= 작거나 같다
	>= 크거나 같다
	============> 숫자만 아니라 문자열도 비교시에 처리
--%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

window.onload=function(){
	let a=10;
	let b=5;
	console.log("a===b"+(a===b)) // false
	console.log("a!==b"+(a!==b)) // true
	console.log("a<b"+(a<b)) // false
	console.log("a>b"+(a>b)) // true
	console.log("a<=b"+(a<=b)) // false
	console.log("a>=b"+(a>=b)) // true
	// 알파벳 A<B<C... 가<나<다...
	let c="Hello";
	let d="JavaScript"
	console.log("c===d"+(c===d)) // false
	console.log("c!==d"+(c!==d)) // true
	console.log("c<d"+(c<d)) // true
	console.log("c>d"+(c>d)) // false
	console.log("c<=d"+(c<=d)) // true
	console.log("c>=d"+(c>=d)) // false
}
</script>
</head>
<body>

</body>
</html>