<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 450px;
}
h1{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.table > thead > tr').css('backgroundColor','#CCCCFF')
	$('.table > tbody > tr:nth-child(2n)').css('backgroundColor',"rgb(255,255,200)")
	$('.table > tbody > tr:nth-child(2n+1)').css('backgroundColor',"rgb(200,155,200)")
	/*
		2n   => n=0 => 0 2 4 ...
		2n+1 => n=0 => 1 3 5 ...
		2n+2 => n=0 => 2 4 6 ...
		3n+1 => n=0 => 1 4 7 ... 
	*/
	/*
		이벤트 
		$(선택자).click(function(){})  => onclick
		$(선택자).change(function(){}) => onchange
		$(선택자).keyup(function(){})  => onkeyup
		$(선택자).mouseover(function(){}) => 
		$(선택자).hover(function(){})  => 
		
		=> 태그의 값을 읽기 / 설정
		   val() ==> val("")
		   text() ==> text("")
		   html() ==> html("")
		             append("")
		   attr("속성") ==> attr("속성","")
		   trim() 
		   ==> 나머지는 자바스크립트 (병행)
	*/
})
</script>
</head>
<body>
  <div class="container">
   <div class="row">
    <table class="table">
     <thead>
       <tr>
         <th class="text-center">번호</th>
         <th class="text-center">이름</th>
         <th class="text-center">성별</th>
       </tr>
     </thead>
     <tbody>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
       <tr>
         <td class="text-center">1</td>
         <td class="text-center">홍길동</td>
         <td class="text-center">남자</td>
       </tr>
     </tbody>
    </table>
   </div>
  </div>
</body>
</html>