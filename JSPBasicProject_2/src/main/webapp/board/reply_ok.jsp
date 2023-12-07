<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<% 
	// 1. 한글 디코딩 
	// => <jsp:useBean> => <jsp:setProperty property="*"> (Bean=VO / 모든 값 채워라)
	request.setCharacterEncoding("UTF-8");
    String name=request.getParameter("name");
    String subject=request.getParameter("subject");
    String content=request.getParameter("content");
    String pwd=request.getParameter("pwd");
    // hidden
    String pno=request.getParameter("pno"); // pno = 답변 대상번호 ParentNo
    String curpage=request.getParameter("page");
    
    /*
                    no gi gs gt root depth
    	AAAAA       1  1  0  0   0     0 => 1로 변경 
    	  =>BBBBB   2  1  1  1   1     0 => 1로 변경
    	    =>CCCCC 3  1  2  2   2     0
    */
    
    
    
    BoardVO vo=new BoardVO();
    vo.setName(name);
    vo.setSubject(subject);
    vo.setContent(content);
    vo.setPwd(pwd);
    
    // public void insert(BoardVO vo) => Spring 방식
    // 오라클 연동 => BoardDAO
    BoardDAO dao=BoardDAO.newInstance();
    dao.boardReplyInsert(Integer.parseInt(pno), vo);
    // 화면 이동 => response.sendRedirect("list.jsp")
    response.sendRedirect("list.jsp?page="+curpage);
    
%>



