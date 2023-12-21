<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	setInterval()  ==> 계속 수행
	setTimeout()   ==> 종료
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
let index=1;
window.onload=function(){
	setInterval('show()', 500); // 500 => 0.5초당 한번씩 show() 수행
}
function show()
{
		index++;
		if(index>7)
			index=1
			let img=document.querySelector('img')
			img.src='../image/m'+index+'.jpg'
}
</script>
</head>
<body>
  <center>
   <img src="../image/m1.jpg" width="600" height="400">
  </center>
</body>
</html>