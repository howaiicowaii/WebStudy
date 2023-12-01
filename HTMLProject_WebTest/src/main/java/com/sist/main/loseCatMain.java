package com.sist.main;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.loseCatDAO;
//import com.sist.dao.loseAniDetailVO;
import com.sist.dao.loseAniVO;
import com.sist.dao.loseCatVO;

public class loseCatMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		
		for(int k=2;k<=30;k++)
		{
		try
		{
			Document doc=Jsoup.connect("http://www.angel.or.kr/index.php?code=cat&page="+k+"&ski=&sci=&sco=&sgu=&q=").get();
			Elements link=doc.select("div.wrapper div.gallery a"); // 2~38p 상세보기들
//			System.out.println(link);
			// 첫번째 칸 (실종) 
			Elements sub=doc.select("div.wrap h3");
//			System.out.println(sub);
			
			// 두번째칸 내용 
			Elements content=doc.select("p.para");
//			System.out.println(content);
			
			// 목록 사진 
			Elements image=doc.select("div.gallery img");
//			System.out.println(image);
			
			for(int i=0;i<sub.size();i++)
			{
				loseAniVO catvo=new loseAniVO();
//				loseAniDetailVO catvo2=new loseAniDetailVO();
				loseCatVO cvo=new loseCatVO();
				
				
//				catvo.setSub(sub.get(i+1).text());
				cvo.setCtitle(sub.get(i+1).text()); // 제목 VO

//				catvo.setContent(content.get(i).text());
				cvo.setCinfo(content.get(i).text()); // 제목 아래 내용 VO
				
				cvo.setCimage(image.get(i).attr("src"));
				String img="http://www.angel.or.kr"+cvo.getCimage().substring(1);
				cvo.setCimage(img); // -- 이미지 VO
				
				String sublink=link.get(i).attr("href");
//				System.out.println(sublink);
				String sublink2="http://www.angel.or.kr/"+sublink;
//				System.out.println(sublink2); // sublink2 가 상세주소
				
				Document doc2=Jsoup.connect(sublink2).get();
				
				// 실종동물 - 상세보기 4개
				Elements detaAni=doc2.select("div.main div.about-us table.table-hover tr");
//				System.out.println(detaAni);
				String anid=detaAni.text();
//				System.out.println(anid);
				String first=anid.substring(anid.indexOf("동물")+3,anid.indexOf("날짜")-2);
//				System.out.println(first); // fisrt => 상세보기 첫줄
//				catvo2.setLoseinfo(first); 
				cvo.setcLoseInfo(first); // 상세 실종동물 VO
				
				String second=anid.substring(anid.indexOf("날짜")+3,anid.indexOf("장소")-2);
//				System.out.println(second); // second => 상세보기 둘째줄 
//				catvo2.setLosedate(second);
				cvo.setcLoseDate(second); // 상세 실종날짜 VO
				
				
				String third=anid.substring(anid.indexOf("장소")+3,anid.indexOf("연락처"));
//				System.out.println(third); // third => 상세보기 셋째줄 
//				catvo2.setLoseloc(third); 
				cvo.setcLoseLoc(third); // 상세 실종장소 VO
				
				
				String fourth=anid.substring(anid.indexOf("특이사항")+5);
//				System.out.println(fourth);
//				catvo2.setFeature(fourth); 
				cvo.setcFeature(fourth); // 상세 특이사항 VO
				
				loseCatDAO dao=new loseCatDAO();
				
				dao.loseCatInsert(cvo);
				
			}
			
		}catch(Exception ex) {}
		}
	}

}