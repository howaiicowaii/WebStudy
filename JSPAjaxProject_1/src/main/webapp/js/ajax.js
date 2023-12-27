let httpRequest=null;
// 브라우저 내장 객체를 생성하는 함수
// XMLHttpRequest => 서버에 요청 전송 => 서버로부터 결과값을 받는 클래스 
function getXMLHttpRequest()
{
	// window.ActiveObject => IE
	if(window.XMLHttpRequest) // => 기타 브라우저 (크롬,파이어폭스)
	{
		return new XMLHttpRequest();
	}
	else
	{
		return null;
	}
}
// 서버에 요청 => find.jsp?fd=aaa
/*
	$.ajax({
		type: post/get
		url: result.jsp => result.jsp?no=1
		data: {no:1}
		success:function(res){
			res => 실행 결과물 => html,json,일반 문자
			=> resText , resXML
		}
	})
*/
function sendRequest(url,params,callback,method)
{
	// XMLHttpRequest 얻기
	httpRequest=getXMLHttpRequest();
	// 전송 방식 처리 <form method="post">
	let httpMethod=method?method:'GET' // null => GET  
	if(httpMethod!='GET' && httpMethod!='POST')
	{
		httpMethod='GET' // GET 이 디폴트
	}
	// params => null 이거나 ''일 경우
	// GET => url?   POST => send()
	let httpParams=(params==null||params=='')?null:params
	let httpUrl=url;
	
	// GET 방식
	if(httpMethod=='GET' && httpParams!=null)
	{
		httpUrl=httpUrl+"?"+httpParams
		// a.jsp?no=1
	}
	
	// 연결 
	httpRequest.open(httpMethod,httpUrl,true)
	// true => 비동기 , false=동기 / ajax => 비동기적. 반드시 true 를 주는 게 정석
	httpRequest.setRequestHeader('Content-Type',
	      "application/x-www-form-urlencoded") // 한글 깨짐 방지
	// 콜백 함수 지정 => 자동호출이 되게 만든다 => 실행 결과값을 받는다
	httpRequest.onreadystatechange=callback
	// 데이터를 전송
	httpRequest.send(httpMethod=='POST'?httpParams:null)
} 




