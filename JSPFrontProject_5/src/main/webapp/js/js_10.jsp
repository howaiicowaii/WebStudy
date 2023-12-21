<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 이벤트 리스너 => --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#box{
	width: 200px;
	height: 200px;
	border: 1px solid black;
}
</style>
<script type="text/javascript">
window.onload=function(){
	let div=document.querySelector("#box")
	// $('#box').mouseup(function(){}) ==> Jquery 방식
	div.addEventListener('mousedown',()=>{
		box.style.background='pink'
	})
	div.addEventListener('mouseup',event=>{
		event.currentTarget.style.background='orange'
	})
}
</script>
</head>
<body>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
  <div id="box"></div>
</body>
</html>