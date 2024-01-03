package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.sist.dao.*;
import com.sist.manager.*;
// 공통모듈 => Spring (AOP) => 메소드 등록
// 시간 => setInterval() => task
public class CommonsModel {
	public static void commonsFooterData(HttpServletRequest request)
	{
		// 1. 공지사항 => hit 가 많은 순으로 넘기려 한다 (일반 사용자는 보기만 할 수 있게)
		NoticeDAO ndao=NoticeDAO.newInstance();
		List<NoticeVO> noList=ndao.noticeTop7();
		request.setAttribute("noList", noList);
		// 2. 맛집 뉴스
		List<NewsVO> nlist=NewsManager.newsSearchData("맛집");
		request.setAttribute("nList", nlist);
		// 3. 인기 맛집 => hit
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> fList=dao.foodTop7();
		request.setAttribute("fList", fList);
	}
}
