<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	response
	  => HttpServletResponse
	  => 응답 정보 
	     = 1. HTML , XML , JSON  
	          setContentType() => 이미 page 지시자에 지정되어 있다
	     = 2. cookie => 브라우저로 전송 
	          ***addCookie()
	     = JSP한파일에서 둘 중에 한개만 전송이 가능하다
	  => 화면 이동 정보
	     = ***sendRedirect() => 서버에서 이동
	       => return "redirect:파일명" ==> Spring 방식
	       => GET 방식
	       => insert => list
	       => update => detail
	       => delete => list
	       => login  => main
	  => 헤더 정보 
	     = ***setHeader() => 헤더 설정
	       => 다운로드 (다운로드 하려면 헤더에 해당 파일 정보 등 출력)
	  => 174page
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>