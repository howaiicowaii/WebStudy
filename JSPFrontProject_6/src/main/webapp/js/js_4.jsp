<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let s="Hello JavaScript"
	//     01234567890
	document.write("s="+s+"<br>")
	let s1=s.substring(0,5);
	//                시작인덱스 , 마지막인덱스-1
	document.write("s1="+s1+"<br>")
	let s2=s.substr(6,4);
	//             시작인덱스 , 갯수
	document.write("s2="+s2+"<br>")
	// vue , react
}
</script>
</head>
<body>

</body>
</html>