package com.sist.view;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		out.write("</body>");
		out.write("</html>");
		// => head , style , script => 닫기를 하지 않는 경우에는 흰색화면만 출력된다
		
	}

}
