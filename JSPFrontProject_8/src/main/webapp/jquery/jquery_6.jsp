<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// 384page => 필요시에는 class추가, class를 삭제할 수 있다
$(function(){
	$('#box').hover(function(){ // if
		$('#box').addClass('hover') // <div class="hover">
	},function(){ // else
		$('#box').removeClass('hover') // <div>
	// 메뉴 => active
	})
})
</script>
<style type="text/css">
#box{
	width: 100px;
	height: 100px;
	background-color: red;
}
#box.hover{
	background-color: blue;
	border-radius: 50px;
}
</style>
</head>
<body>
  <div id="box"></div>
</body>
</html>