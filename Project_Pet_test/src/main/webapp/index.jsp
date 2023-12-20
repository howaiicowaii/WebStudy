<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	

<%--
       .do ==> 처리
       .jsp ==> 파일 출력
       ------------------------------
       현재는 DispatcherServlet에 forward()기법으로
       jsp/html만 다르게 나오고 있는거임
       예전에 frame과 panel 기법과 비슷한듯
       ------------------------------
       ~project index 파일~
       [코딩하는 순서]
       0. site.jsp를 하나 만듬
       1. header.jsp <a>에 site.do 링크를 검
       2. applicaion.xml에 추가한 SeoulModel 클래스를 새로 만듬 
       3. applicaion.xml에 경로(=SeoulModel 클래스)를 추가함
       3-1. 먼저 만들고 추가해야 오류가 안남!! 근데 그다지 중요하지 않은듯...
       4. SeoulModel 클래스에서 @RequestMapping을 만듬
 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
location.href="main/main.do";
</script>
</head>
<body>
</body>
</html>