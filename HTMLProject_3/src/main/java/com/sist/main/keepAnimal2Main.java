package com.sist.main;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.keepAnimal2DAO;
import com.sist.dao.keepAnimal2VO;

public class keepAnimal2Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		
		for(int k=1;k<=30;k++)
		{
		try
		{
		Document doc=Jsoup.connect("https://singlesumer.com/protect/page/"+k).get();
		Elements link=doc.select("td.title a.hx"); // 상세 미완 링크
//		System.out.println(link);
		// 한개VO => 고유번호,지역,제목,글쓴이,날짜,발견장소,사진,내용 (댓글기능)
		
		// 지역 
		Elements kloc=doc.select("tbody td.cate span");
//		System.out.println(kloc);
		
		// 제목 
		Elements kt=doc.select("tbody td.title a.hx");
//		System.out.println(kt);
		
		// 글쓴이 (i+1 해야한다) -> 공지도 출력 
		Elements kw=doc.select("tbody td.author a");
//		System.out.println(kw);
		
		// 날짜 (i+1 해야한다) -> 공지도 출력 
		Elements kdate=doc.select("tbody td.time");
//		System.out.println(kdate);
		
		for(int i=0;i<kloc.size();i++)
		{
			keepAnimal2VO kvo=new keepAnimal2VO();
			
			kvo.setKeepLoc(kloc.get(i).text()); // 지역 VO
//			System.out.println(kvo.getKeepLoc());
			kvo.setKeepTitle(kt.get(i).text()); // 제목 VO
//			System.out.println(kvo.getKeepTitle());
			kvo.setKeepWriter(kw.get(i+1).text()); // 글쓴이 VO
//			System.out.println(kvo.getKeepWriter());
			kvo.setKeepRegDate(kdate.get(i+1).text()); // 날짜 VO
//			System.out.println(kvo.getKeepRegDate());
			
			String sublink=link.get(i).attr("href");
//			System.out.println(sublink); 
			String link2="https://singlesumer.com"+sublink;
//			System.out.println(link2); // link2 => 상세주소 링크 
			Document doc2=Jsoup.connect(link2).get();
			
			// 발견장소(상세)
			Elements kfloc=doc2.select("table.et_vars.bd_tb tr.bg1 td").eq(0);
//			System.out.println(kfloc.text());
			String kfloc2=kfloc.text();
			kvo.setKeepFoundLoc(kfloc2); // 발견장소 VO
//			System.out.println(kvo.getKeepFoundLoc());
			
			// 사진 (상세)
			Elements kimage=doc2.select("div.rd_body.clear img").eq(0);
//			System.out.println(kimage);
			// https://singlesumer.com/files/attach/images/130/539/176/50cc3c0ce83ec5607f122a4647474864.jpg
			String kimage2="https://singlesumer.com/"+kimage.attr("src");
//			System.out.println(kimage2);
			kvo.setKeepImage(kimage2); // 사진 VO
//			System.out.println(kvo.getKeepImage());
			
			// 내용 (상세)
			Elements kcontent=doc2.select("div.rd_body.clear article");
//			System.out.println(kcontent);
			String kcontent2=kcontent.text();
//			System.out.println(kcontent2);
			kvo.setKeepContent(kcontent2); // 내용 VO
//			System.out.println(kvo.getKeepContent());
			
			keepAnimal2DAO dao=new keepAnimal2DAO();
			
			dao.keepAnimal2Insert(kvo);
			
		}
		System.out.println("Save end...");
		}catch(Exception ex) {}
		}
	}

}