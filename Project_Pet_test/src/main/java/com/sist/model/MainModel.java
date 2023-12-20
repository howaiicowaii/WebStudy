package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

public class MainModel {
	@RequestMapping("main/main.do")
	public String main_main(HttpServletRequest request, HttpServletResponse response)
	{
		
		String fd=request.getParameter("fd");
		if(fd==null) {
			fd="가평";
		}
		StayDAO dao=StayDAO.newInstance();
		List<StayVO> sList=dao.stayAddressListData(fd);
		ProductDAO pdao=ProductDAO.newInstance();
		List<ProductVO>plist =pdao.homeProduct();
		AnimalDAO adao=AnimalDAO.newInstance();
		List<AnimalVO> alist=adao.animalBlogList();
		
		request.setAttribute("plist", plist);
		request.setAttribute("alist", alist);
		request.setAttribute("fd", fd);
		request.setAttribute("sList", sList);
		request.setAttribute("main_jsp", "../main/home.jsp");
		return "../main/main.jsp";
	}
}
