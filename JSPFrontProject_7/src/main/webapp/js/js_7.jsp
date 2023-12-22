<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	JSON (JavaScript Object Notation)
	 => 자바스크립트 객체 표현법
	 => key,value 쌍으로 구성된 형태의 객체 표기법
	    {key:value} => map 형식 => key는 중복되면 안된다
	     === 자바(멤버변수), 오라클(컬럼명)
	     === 단점
	         {a:1,b:2,c:3} => {a:2,b:3} => {c:3}
	         ==> 보통 => [{데이터,curpage:1,totalpage:10},{데이터}...]
	 => JSON 목적 : 클라이언트와 서버 사이의 데이터 교환 목적으로 사용 
	    요청값 전송 : JSON
	                data:{no:1,id:'aaa'}
	                params:{}
	    응답값 받기 : JSON
	 => 서버에서 JSON을 전송 => 문자열
	    클라이언트에서는 JSON.parse()
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let info={name:"홍길동",age:20,sex:"남자"}; 
	console.log(info)
	let data=JSON.stringify(info) 
	console.log(data) // 문자열 로 출력. JSON 은 서버에서 데이터를 가져오면 문자열로 출력한다?
	let change=JSON.parse(data)
	console.log(change) // Object 로 출력
}
</script>
</head>
<body>

</body>
</html>