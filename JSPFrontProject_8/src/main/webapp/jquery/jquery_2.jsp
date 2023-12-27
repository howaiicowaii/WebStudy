<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('div > h1+span').css("color","red")
	// $('div > h1~span').css("color","red") => Hello Selector1 하고 2 둘 다 적용
})
</script>
</head>
<body>
  <div>
   <h1>Hello Jquery!!</h1>
   <span>Hello Selector1</span>
   <span>Hello Selector2</span>
  </div>
</body>
</html>