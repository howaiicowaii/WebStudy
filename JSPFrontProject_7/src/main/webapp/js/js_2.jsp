<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 형변환 : Number() , parseInt() --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row{
	margin: 0px auto;
	width: 450px;
}
</style>
<script type="text/javascript">
window.onload=function(){
	let btn=document.getElementById("calcBtn")
	btn.addEventListener('click',function(){ // calcBtn 버튼 눌렀을 때 해당 펑션 실행
		let kor=document.getElementById("kor")
		if(kor.value.trim()==="") // kor 이 공백이라면 (trim 필수)
		{
			kor.focus()
			return;
		}
	
		let eng=document.getElementById("eng")
		if(eng.value.trim()==="") // eng 이 공백이라면 (trim 필수)
		{
			eng.focus()
			return;
		}
		
		let math=document.getElementById("math")
		if(math.value.trim()==="") // math 이 공백이라면 (trim 필수)
		{
			math.focus()
			return;
		}
		////////////////////// 유효성 검사 ! ==> 반드시 입력
		let total=Number(kor.value)+parseInt(eng.value)+Number(math.value)
		document.querySelector('#total').value=total;
		
		let avg=total/3
		document.querySelector('#avg').value=Math.round(avg,2); // 두자리만 나오게 반올림
	})
}
</script>
</head>
<body>
  <div class="container">
   <div class="row">
     <h1>성적 계산</h1>
     <table class="table">
     
      <tr>
       <td class="text-center" width=30%>국어</td>
       <td width=70%>
        <input type="text" id=kor size=15 class="input-sm">
       </td>
      </tr>
      
      <tr>
       <td class="text-center" width=30%>영어</td>
       <td width=70%>
        <input type="text" id=eng size=15 class="input-sm">
       </td>
      </tr>
      
      <tr>
       <td class="text-center" width=30%>수학</td>
       <td width=70%>
        <input type="text" id=math size=15 class="input-sm">
       </td>
      </tr>
      
      <tr>
       <td class="text-center" width=30%>총점</td>
       <td width=70%>
        <input type="text" id=total size=15 class="input-sm" readonly>
       </td>
      </tr>
      
      <tr>
       <td class="text-center" width=30%>평균</td>
       <td width=70%>
        <input type="text" id=avg size=15 class="input-sm" readonly>
       </td>
      </tr>
      
      <tr>
       <td colspan="2" class="text-center">
        <input type=button value="계산"
         class="btn-sm btn-primary" id="calcBtn">
       </td>
      </tr>
      
     </table>
   </div>
  </div>
</body>
</html>