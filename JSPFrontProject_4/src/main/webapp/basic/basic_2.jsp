<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트에서 제공하는 제어문
	조건문
	 단일 조건문
	  ***if(비교연산자|논리연산자|부정연산자) -> 조건
	  {
	     조건이 true일 때 수행하는 문장
	  }
	  
	 선택 조건문 => 삼항연산자로 변경
	  ***if(조건문)
	  {
	     조건이 true일 때 처리
	  }
	  else
	  {
	     조건이 false일 때 처리
	  }
	  
	 다중조건문
	  if(조건문)
	  {
	     조건이 true일 때 수행 ===> 종료
	     조건이 false일 때는 아래 있는 if조건문으로 이동
	  }
	  else if(조건문)
	  {
	     조건이 true일 때 수행 ===> 종료
	     조건이 false일 때는 아래 있는 if조건문으로 이동
	  }
	  else if(조건문)
	  {
	     조건이 true일 때 수행 ===> 종료
	     조건이 false일 때는 아래 있는 if조건문으로 이동
	  }
	  ...
	  else
	  {
	     해당 조건이 없는 경우에 수행하는 문장
	  }
	  
	  ===> 사용처 : 사용자 입력창에서 많이 사용 (글쓰기,회원가입,로그인,우편번호)
	선택문 : switch
	       switch(정수)
	       {
	          case 값:
	             처리문장
	             break;
	          ...
	          ...
	          default: 처리문장
	       }
	반복문
	     do~while
	     while : 무한 스크롤
	  ***for
	     일반 for
	     for in
	     for of    => for-each for(String name:list)
	     ***map()  =>
	     forEach() =>
	반복제어문
	     ***break / continue
 --%>
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
	width: 350px;
}
h1{
	text-align: center
}
</style>
<script type="text/javascript">
function login()
{
		// 1. id 읽기 (입력한)
		// 객체 모델 => 10장 (태그를 객체로 인식 , 속성 : 멤버변수로 인식)
		let id=document.querySelector("#id"); // 태그를 가지고 온다
		// $('#id') => target.value => $ref
		if(id.value==="") // value 가 공백이라면. ===은 문자열도 처리한다
		{
			// 입력이 안된 경우
			let div=document.querySelector(".id")
			div.innerHTML="<font color=red>아이디를 입력하세요</font>";
			id.focus();
			return;
		}
		else
		{
			// 입력이 된 경우
			let div=document.querySelector(".id")
			div.innerHTML="";
		}
		
		let pwd=document.querySelector("#pwd");
		
		if(pwd.value==="")
		{
			let div=document.querySelector(".pwd")
			div.innerHTML="<font color=red>비밀번호를 입력하세요</font>";
			pwd.focus();
			return;
		}
		else
		{
			// 입력이 된 경우
			let div=document.querySelector(".id")
			div.innerHTML="";
		}
	
}
</script>
</head>
<body>
  <div class="container">
   <h1>Login</h1>
   <div class="row">
    <table class="table">
    
     <tr>
      <th width="20%" class="text-center">ID</th>
      <td width="80%">
       <input type="text" id="id" size=15 class="input-sm">
       <div class="id"></div>
      </td>
     </tr>
     
     <tr>
      <th width="20%" class="text-center">PW</th>
      <td width="80%">
       <input type="password" id="pwd" size=15 class="input-sm">
       <div class="pwd"></div>
      </td>
     </tr>
     
     <tr>
      <td colspan="2" class="text-center">
       <input type="button" value="로그인"
        class="btn-sm btn-primary" onclick="login()">
      </td>
     </tr>
    </table>
   </div>
  </div>
</body>
</html>