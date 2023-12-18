<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.container{
	width: 100%;
	margin: 0px auto;
	border: 1px solid black;
}
header{
	width: 100%;
	height: 100px;
	background-color: yellow;
}
aside{
	float: left;
	width: 30%;
	height: 600px;
	background-color: pink;
}
.article1{
	float: left;
	width: 70%;
	height: 200px;
	background-color: red;
}
.article2{
    float: left;
	width: 70%;
	height: 200px;
	background-color: green;
}
.article3{
	float: left;
	width: 70%;
	height: 200px;
	background-color: blue;
}
footer{
	clear: both;
	width: 100%;
	height: 100px;
	background-color: gray;
}
/* 960px */
@media screen and (max-width:960px){
	.container{width: 100%}
	.article1{height: 300px}
	.article2{height: 300px}
	.article3{height: 300px}
}
/* 768px */
@media screen and (max-width:768px){
 aside{width:100%;height: 300px}
	.article1{height: 50%}
	.article2{height: 50%}
}
/* 480px */
@media screen and (max-width:480px){
	.article1{height: 100%}
	.article2{height: 100%}
}
</style>
</head>
<body>
  <div class="container">
   <header></header>
   <aside></aside>
   <section class="article1"></section>
   <section class="article2"></section>
   <section class="article3"></section>
   <footer></footer>
  </div>
</body>
</html>