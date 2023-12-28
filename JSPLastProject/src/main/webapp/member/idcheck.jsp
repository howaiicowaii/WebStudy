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
	margin-top: 20px;
}
.row{
	margin: 0px auto;
	width: 300px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#idcheckBtn').click(function(){
		/*let id=$('#id').val()
		if(id.trim()==="")
		{
			$('#id').focus()
			return; // 중복체크 쪽 입력 안하면 입력칸에 포커스 가게끔
		}*/
		// 서버로 전송 (Ajax => 보내고 받기 동시에 가능)
		let id=$('#id').val();
		$.ajax({
			type:'post',
			url:'../member/idcheck_ok.do',
			data:{"id":id},
			success:function(result)
			{
				let count=parseInt(result);
				if(count===0)
				{
					$('#result').html('<font color="blue">'+id+'는(은) 사용 가능한 ID입니다</font>');
					$('#okBtn').show(); // '확인'버튼 숨겨져있던 거 나타나게
				}
				else
				{
					$('#result').html('<font color="red">'+id+'는(은) 이미 사용중인 ID입니다</font>');
					$('#id').val(""); // 사용중에 있으니 아이디 지워주고
					$('#id').focus();
				}
			}
		})
	})
	
	$('#okBtn').click(function(){
		parent.frm.id.value=$('#id').val()
		parent.Shadowbox.close()
		// parent = join.jsp
	})
})
</script>
</head>
<body>
  <div class="container">
   <div class="row">
    <table class="table">
     <tr>
       <td>입력:<input type="text" size=15 class="input-sm" id="id">
         <input type="button" value="아이디중복체크"
          class="btn btn-sm btn-success" id="idcheckBtn">
       </td>
     </tr>
     <tr>
       <td><span id="result"></span></td>
     </tr>
     <tr>
       <td class="text-center"><input type="button" class="btn btn-sm btn-danger" value="확인" style="display:none" id="okBtn"></td>
     </tr>
    </table>
   </div>
  </div>
</body>
</html>