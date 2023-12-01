package com.sist.main;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.loseAniDAO;
import com.sist.dao.loseAniVO;

import oracle.jdbc.driver.json.parser.JsonLocationImpl;

public class loseAniMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list=new ArrayList<String>();
		
		for(int k=2;k<=30;k++)
		{
		try
		{
		Document doc=Jsoup.connect("http://www.angel.or.kr/index.php?code=dog&page="+k+"&ski=&sci=&sco=&sgu=&q=").get();
		Elements link=doc.select("div.wrapper div.gallery a"); // 2~38p 상세보기들
//		System.out.println(link);
		// 첫번째 칸 (실종)
		Elements sub=doc.select("div.wrap h3");
//		System.out.println(sub); 
		
		// 두번째칸 내용 
		Elements content=doc.select("p.para");
//		System.out.println(content);
		
		// 목록 사진 
		Elements image=doc.select("div.gallery img");
//		System.out.println(image);
		
		for(int i=0;i<sub.size();i++)
		{
			loseAniVO dvo=new loseAniVO();
//			loseAniDetailVO dvo2=new loseAniDetailVO(); 
			
//			dvo.setSub(sub.get(i+1).text());
//			dvo.setContent(content.get(i).text());
			dvo.setImage(image.get(i).attr("src")); // ii->vo.setimage 가 진짜 사진 링
//			System.out.println(vo.getSub()); // 맨 앞꺼 빼야한다 => i+1로 뺐다
//			System.out.println(vo.getContent());
//			System.out.println(vo.getImage());
			String ii="http://www.angel.or.kr"+dvo.getImage().substring(1);
			dvo.setImage(ii); // -- 이게 목록 이미지 VO
//			System.out.println(dvo.getImage()); //-- 이게 목록 이미지 최종
			
			String sublink=link.get(i).attr("href");
//			System.out.println(sublink);
			String sublink2="http://www.angel.or.kr/"+sublink;
//			System.out.println(sublink2); // sublink2 가 상세주소

			Document doc2=Jsoup.connect(sublink2).get();
			
//			상세보기 - 위 제목,	정보 + 고유번호 
			// 고유번호 -> 겹쳐서 나오는 값들 있어서 미뤄놓기..
//			Elements ldnoo=doc2.select("div.main div.specials div.gallery");
//			System.out.println(ldnoo);
			
			
			// 제목 Done
			Elements titleDet=doc2.select("div.main h2.style");
//			System.out.println(posterDet);
			String posterr=titleDet.text();
			String detatitle=posterr.substring(posterr.indexOf("("));
//			System.out.println(detatitle); // detatitle 이 상세보기 title
			dvo.setTitle(detatitle); // 상세 제목(title) VO
			
			// 글 정보 Done
			Elements infoo=doc2.select("div.main div.about-info p.para");
//			System.out.println(infoo);
			String info=infoo.text();
//			System.out.println(info); // info = 글 정보 
			dvo.setInfo(info); // 상세 제목 아래 info VO
			
			// 실종동물 - 상세보기 4개
			Elements detaAni=doc2.select("div.main div.about-us table.table-hover tr");
//			System.out.println(detaAni);
			String anid=detaAni.text();
//			System.out.println(anid);
			String first=anid.substring(anid.indexOf("동물")+3,anid.indexOf("날짜")-2);
//			System.out.println(first); // fisrt => 상세보기 첫줄
			dvo.setLoseinfo(first); // 상세 실종동물 VO
			
			String second=anid.substring(anid.indexOf("날짜")+3,anid.indexOf("장소")-2);
//			System.out.println(second); // second => 상세보기 둘째줄 
			dvo.setLosedate(second); // 상세 실종날짜 VO
			
			String third=anid.substring(anid.indexOf("장소")+3,anid.indexOf("연락처"));
//			System.out.println(third); // third => 상세보기 셋째줄 
			dvo.setLoseloc(third); // 상세 실종장소 VO
			
			String fourth=anid.substring(anid.indexOf("특이사항")+5);
//			System.out.println(fourth);
			dvo.setFeature(fourth); // 상세 특이사항 VO
			
//			loseAniVO vo=new loseAniVO();
			loseAniDAO dao=new loseAniDAO();
//			vo.setSub(sub.get(i).text());
//			vo.setImage(image.get(i).text());
//			vo.setContent(content.get(i).text());
			
			dao.loseAniInsert(dvo);
			
			
		}
		System.out.println("Save end...");
		
		}catch(Exception ex) {}
		}
	}
	

}
