package com.sist.change;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String html="<div class=\"row\">\n"
				+ "		  <table class=\"table\">\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"35%\" align=\"center\" rowspan=\"9\">\n"
				+ "					  <img src=# id=\"image\">\n"
				+ "				  </td>\n"
				+ "				  <td width=\"65%\" align=\"center\">\n"
				+ "					  <span id=\"title\">\n"
				+ "					  </span>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <span id=\"sub\"></span>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <span id=\"percent\">%</span>&nbsp;\n"
				+ "					  <span id=\"price\"></span>\n"
				+ "					  <p>\n"
				+ "						  <del id=\"psub\">17,900원</del>\n"
				+ "					  </p>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <span id=\"label\">첫구매할인가</span>\n"
				+ "					  <span id=\"price2\"></span>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <span id=\"star\">★★★★★</span>&nbsp;\n"
				+ "					  <span id=\"bold\">4.5</span>\n"
				+ "					  <span id=\"count\">(299)</span>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <img src=\"https://recipe1.ezmember.co.kr/img/mobile/icon_delivery3.png\">\n"
				+ "					  <span id=\"dform\">배송</span>\n"
				+ "				  	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n"
				+ "				  	  <span id=\"delivery\"></span>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <select id=\"sel\">\n"
				+ "						  <option>옵션 선택</option>\n"
				+ "						  <option>222</option> "
				+ "					  </select>\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "			  <tr>\n"
				+ "				  <td width=\"65%\">\n"
				+ "					  <input type=\"button\" value=\"장바구니\" id=\"cart\">\n"
				+ "					  <input type=\"button\" value=\"바로구매\" id=\"buy\">\n"
				+ "				  </td>\n"
				+ "			  </tr>\n"
				+ "		  </table>\n"
				+ "	  </div>";
		System.out.println(html);
	}

}
