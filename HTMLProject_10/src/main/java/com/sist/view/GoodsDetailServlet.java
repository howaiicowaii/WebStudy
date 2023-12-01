package com.sist.view;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;

@WebServlet("/GoodsDetailServlet")
public class GoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
// out.write("<link rel=\"stylesheet\" href=\"css/style.css\">");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// ISO-8859_1 : default => 한글 다 깨진다
		PrintWriter out=response.getWriter(); // out 에 데이터들 놓는다
		// 클라이언트가 보낸 데이터 받기 
		// MainServlet?mode=5&no="+vo.getNo()+"&type=1
		// ================== ======================== from GoodsAllServlet
		//  mode => 화면 변경    DetailServlet (include된 각 Servlet들이 처리할 부분)
		String no=request.getParameter("no");
		String type=request.getParameter("type"); // goodsDetailData 매개변수 no,type 받는다
		// DAO 연동 
		GoodsDAO dao=GoodsDAO.newInstance();
		GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no),
				Integer.parseInt(type));
		
		// 화면 출력 
		out.write("<html>");
		out.write("<head>");
		out.write("<link rel=\"stylesheet\" href=\"css/style.css\">");
		out.write("<script type=text/javascript src=\"http://code.jquery.com/jquery.js\"></script>");
		out.write("<script type=text/javascript>");
		out.write("let i=0;");
		// 자바스크립트 => 자동 변환 변수 ==> let i=0; i는 자동으로 int
		// let a="aaa"; 하면 a는 String / let d=10.5; 하면 d는 자동으로 double
		// let o={}; 하면 o는 Object, let k=[] 하면 k는 Array
		// var(예전방식) => scope(사용범위)에 단점이 있어서 let 자동 변환 변수로 바뀌었다
		out.write("$(function(){");
		out.write("$('.ups').click(function(){"); // .ups => 수정버튼 전부 다
		out.write("$('.updates').hide();");
		out.write("let a=$(this).attr('data-no');"); // a 에 번호 읽어왔고 
		out.write("if(i==0){");
		out.write("$('#m'+a).show();"); // 읽어온 번호 중 id=m 인 댓글의 수정 보여준다
		out.write("$(this).text('취소');"); // this => 클릭한 대상(내가 클릭한 수정)
		out.write("i=1;");
		out.write("}else{");
		out.write("$('#m'+a).hide();");
		out.write("$(this).text('수정');");
		out.write("i=0;");
		out.write("}");
		out.write("})");
		out.write("})");
		out.write("</script>");
		out.write("</head>");
		out.write("<body>");
		String html="<div class=\"row\"> "
				+ "		  <table class=\"table\"> "
				+ "			  <tr> "
				+ "				  <td width=\"35%\" align=\"center\" rowspan=\"9\"> "
				+ "					  <img src="+vo.getPoster()+" id=\"image\"> "
				+ "				  </td> "
				+ "				  <td width=\"65%\" align=\"center\"> "
				+ "					  <span id=\"title\"> "
				+vo.getName()
				+ "					  </span> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <span id=\"sub\">"+vo.getSub()+"</span> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <span id=\"percent\">"+vo.getDiscount()+"%</span>&nbsp; "
				+ "					  <span id=\"price\">"+vo.getPrice()+"</span> "
				+ "					  <p> "
				+ "						  <del id=\"psub\">17,900원</del>  "
				+ "					  </p> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <span id=\"label\">첫구매할인가</span> "
				+ "					  <span id=\"price2\">"+vo.getFprice()+"</span> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <span id=\"star\">★★★★★</span>&nbsp; "
				+ "					  <span id=\"bold\">4.5</span> "
				+ "					  <span id=\"count\">(299)</span> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <img src=\"https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png\"> "
				+ "					  <span id=\"dform\">배송</span> "
				+ "				  	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
				+ "				  	  <span id=\"delivery\">"+vo.getDelivery()+"</span> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <select id=\"sel\"> "
				+ "						  <option>옵션 선택</option> "
				+ "						  <option>222</option> "
				+ "					  </select> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "			  <tr> "
				+ "				  <td width=\"65%\"> "
				+ "					  <input type=\"button\" value=\"장바구니\" id=\"cart\"> "
				+ "					  <input type=\"button\" value=\"바로구매\" id=\"buy\"> "
				+ "				  </td> "
				+ "			  </tr> "
				+ "		  </table> "
				+ "	  </div>";
		
		out.write(html);
		
		out.write("<div style=\"height=30px\"></div>");
		out.write("<div class=row>"); // row 한줄 !
		// 댓글 출력 
		out.write("<table class=table>");
		out.write("<tr>");
		out.write("<td>");
		ReplyDAO rdao=ReplyDAO.newInstance();
		List<ReplyVO> list=rdao.replyListData(Integer.parseInt(type), Integer.parseInt(no));
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		for(ReplyVO rvo:list)
		{
			out.write("<table class=table>");
			out.write("<tr>");
			out.write("<td class=text-left>");
			out.write("◑"+rvo.getName()+"&nbsp;("+rvo.getDbday()+")");
			out.write("</td>");
			out.write("<td class=text-right>");
			if(rvo.getId().equals(id)) // 세션에 저장된 id와 rvo에서 가져온 id 가 같으면 = 본인이면
			{
				out.write("<span class=\"btn btn-xs btn-success ups\" data-no="+rvo.getRno()+">수정</span>&nbsp;");
				out.write("<a href=ReplyDeleteServlet?rno="+rvo.getRno()+"&type="+type+"&no="+no+" class=\"btn btn-xs btn-info\">삭제</a>");
			}
			out.write("</td>");
			out.write("</tr>");
			
			out.write("<tr>");
			out.write("<td colspan=2>");
			out.write("<pre style=\"white-space:pre-wrap;background-color:white;border:none\">"+rvo.getMsg()+"</pre>");
			out.write("</td>");
			out.write("</tr>");
			
			out.write("<tr id=m"+rvo.getRno()+" class=updates style=\"display:none\">"); // display:none => 안보여줬다가 '수정'버튼 누르면 뜨게 만들어야 한다
			out.write("<td colspan=2>");
			
			out.write("<form method=post action=ReplyUpdateServlet>");
			out.write("<input type=hidden name=rno value="+rvo.getRno()+">");
			out.write("<input type=hidden name=gno value="+no+">"); // hidden 값은 보여주지 않고 데이터 보낸다
			out.write("<input type=hidden name=typeno value="+type+">");
			out.write("<textarea name=msg rows=4 cols=60 style=\"float:left\">"+rvo.getMsg()+"</textarea>"); // 바뀐 댓글내용 출력
			out.write("<input type=submit value=\"댓글 수정\" style=\"width:100px;height:87px;background:blue;color:white;\">");
			out.write("</form>");
			// 처리하는 서블릿(JSP) / 화면 출력 서블릿(JSP) => Main
			// => 화면이 없는 (HTML 없는상태) => 자체에서 처리
			out.write("</td>");
			out.write("</tr>");
			out.write("</table>");
		}
		out.write("</td>");
		out.write("</tr>");
		out.write("</table>");
		 // 리턴형이 Object 여서 형변환 필요
		// 댓글 작성
		if(id!=null) // 로그인이 됐다면 (id가 null 이 아니니깐)
		{
			out.write("<form method=post action=GoodsDetailServlet>");
			out.write("<input type=hidden name=gno value="+no+">"); // hidden 값은 보여주지 않고 데이터 보낸다
			out.write("<input type=hidden name=typeno value="+type+">");
			out.write("<textarea name=msg rows=4 cols=60 style=\"float:left\"></textarea>");
			out.write("<input type=submit value=\"댓글 쓰기\" style=\"width:100px;height:87px;background:blue;color:white;\">");
			// style=float:left 아래 말고 위에꺼를 왼쪽에 두고 그 오른쪽에 붙여라
			out.write("</form>");
		}
		out.write("</div>");
		out.write("</body>");
		out.write("</html>");
		// => head , style , script => 닫기를 하지 않는 경우에는 흰색화면만 출력된다
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 한글 변환 
		try
		{
			req.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String gno=req.getParameter("gno");
		String typeno=req.getParameter("typeno");
		String msg=req.getParameter("msg");
		
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		
		ReplyVO vo=new ReplyVO();
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		vo.setGno(Integer.parseInt(gno));
		vo.setTypeno(Integer.parseInt(typeno));
		
		// DAO에 연동 
		ReplyDAO dao=ReplyDAO.newInstance();
		dao.replyInsert(vo); // 댓글 INSERT
		
		// 이동 (화면 이동)
		resp.sendRedirect("MainServlet?mode=5&no="+gno+"&type="+typeno); // mode=5 가 Detail
		/*
			request : 클라이언트에 대한 정보 
			          ip / port ...
			          사용자가 보낸 정보 getParameter() 를 통해 받는다 
			          사용자가 보낸 모든 정보는 request 가 갖고 있다
			response : 응답 정보 / 헤더 정보
			           =======
			           | HTML 전송 => setContentType
			             화면 이동 정보 => sendRedirect() => 서버에서 화면 바꿔주는 
			=> JSP 동일 
			   내장 객체 => request,response,session
			   => Spring에서 JSP는 동일
		*/
		
	}
	

}
