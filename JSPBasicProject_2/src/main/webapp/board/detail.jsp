<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	/*
		1. 회원가입, 회원수정 
		   예매 , 글쓰기 ... 입력창 / 처리창 따로 만들어야 한다 => jsp 2개 필요 (ㅁㅁ_ok.jsp)
		   = INSERT / UPDATE / DELETE 는 입력창 / 처리창 구분
	*/
	// 1. 요청 데이터 받기
	// ?no= &page=
	// request.setCharacterEncoding() => 한글 디코딩 왜 안썼을까 => 지금은 알파벳,숫자만 넘어오는 상황이기 때문에
	// => 무조건 쓰는 건 아니고 한글이 넘어올 때만 사용! (한글 깨짐 방지니까)
	String no=request.getParameter("no");
	String curpage=request.getParameter("page");
	// 2. 오라클에서 데이터를 가지고 온다 => DAO
    BoardDAO dao=BoardDAO.newInstance();
	BoardVO vo=dao.boardDetailData(Integer.parseInt(no));
	// 3. 화면 출력
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/table.css">
<style type="text/css">
	img#main{
		margin-top: 50px;
	}
	a{
		color: black;
		text-decoration: none;
	}
	a:hover{
		color: green;
		text-decoration: underline;
	}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0; // 전역변수
$(function(){
	// window.onload=function(){}
	$('#delBtn').click(function(){
		if(i==0)
		{
			$('#del').show();
			$('#delBtn').text("취소");
			$('#pwd').focus(); // id="pwd" 태그 불러와서 포커스 시킨다
			// document.getElementById("pwd"); 위가 이걸 줄인 jquery 문장이다.
			i=1;
		}
		else
		{
			$('#del').hide();
			$('#delBtn').text("삭제");
			i=0;
		}
		
	});
});
</script>
</head>
<body>
  <center>
    <img id="main" src="https://i0.wp.com/aikidonews.co.kr/wp-content/uploads/2017/03/QA-e1488530256249.jpg?resize=500%2C269" width="500" height="100">
    <table class="table_content" width=800>
      <tr>
        <th width=20%>번호</th>
        <td width=30% class="text-center"><%=vo.getNo() %></td>
        <th width=20%>작성일</th>
        <td width=30% class="text-center"><%=vo.getDbday() %></td>
      </tr>
      <tr>
        <th width=20%>이름</th>
        <td width=30% class="text-center"><%=vo.getName() %></td>
        <th width=20%>조회수</th>
        <td width=30% class="text-center"><%=vo.getHit() %></td>
      </tr>
      <tr>
        <th width=20%>제목</th>
        <td colspan=3><%=vo.getSubject() %></td>
      </tr>
      <tr>
        <td colspan="4" class="text-left" valign="top" height="200"><pre style="white-space: pre-wrap;"><%=vo.getContent() %></pre></td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
         <a href="reply.jsp?no=<%=no%>&page=<%=curpage%>">답변</a>&nbsp;
         <a href="update.jsp?no=<%=no%>&page=<%=curpage%>">수정</a>&nbsp;
         <span id="delBtn" style="cursor:pointer">삭제</span>&nbsp;
         <a href="list.jsp?page=<%=curpage%>">목록</a> 
         <!-- 여기 history.back() 주면 안된다 
              조회수 증가 제대로 되러면 list.jsp 불러와야 한다 
              history.back() 하면 조회수 증가가 안된다 (back은 취소할 때만)
         -->
        </td>
      </tr>
      <tr style="display:none" id="del">
        <td colspan="4" class="text-right">
        <form method="post" action="delete.jsp">
         <input type="hidden" name=no value="<%=no%>">
         <input type="hidden" name=page value="<%=curpage%>">
         비밀번호:<input type="password" name=pwd size=15 id="pwd" required>
         <button>삭제</button>
        </form>
        </td>
      </tr>
    </table>
    </center>
</body>
</html>