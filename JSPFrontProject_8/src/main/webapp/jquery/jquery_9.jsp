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
.charrow{
	width: 450px;
	height: 500px;
	margin: 0px auto;
}
#chatArea{
	height: 250px;
	overflow-y:auto;
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#sendBtn').on('click',function(){
		let data=$('#sendMsg').val()
		if(data.trim()=="")
		{
			$('#sendMsg').focus()
			return;
		}
		$('#recvMsg').append(data+"<br>") // 입력한 채팅 출력 
		// 스크롤이 가장 최신을 보여줄 수 있게 
		let ch=$('#chatArea').height()
		let m=$('#recvMsg').height()-ch
		$('#chatArea').scrollTop(m) // 위치를 항상 m쪽에
		$('#sendMsg').val("")
		$('#sendMsg').focus()
	})
	$('#sendMsg').keydown(function(key){
		if(key.keyCode==13) // 엔터 쳐서 입력 (위 코드는 버튼 클릭시였다) keyCode=13 은 엔터.
		{
			let data=$('#sendMsg').val()
			if(data.trim()=="")
			{
				$('#sendMsg').focus()
				return;
			}
			$('#recvMsg').append(data+"<br>") // 입력한 채팅 출력 
			// 스크롤이 가장 최신을 보여줄 수 있게 
			let ch=$('#chatArea').height()
			let m=$('#recvMsg').height()-ch
			$('#chatArea').scrollTop(m) // 위치를 항상 m쪽에
			$('#sendMsg').val("")
			$('#sendMsg').focus()
		}
	})
})
</script>
</head>
<body>
  <div class="container">
   <h1>실시간 채팅</h1>
   <div class="row chatrow">
     <table class="table">
       <tr>
         <td>
          <div id="chatArea">
            <div id="recvMsg"></div>
          </div>
         </td>
       </tr>
       <tr>
        <td>
         <input type=text id="sendMsg" size=50 class="input-sm">
         <input type=button id="sendBtn" value=전송
          class="btn btn-sm btn-primary">
        </td>
       </tr>
     </table>
   </div>
  </div>
</body>
</html>