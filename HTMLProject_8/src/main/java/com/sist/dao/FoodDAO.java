package com.sist.dao;
// DBCP => 미리 Connection을 연결 시켜놓고 => POOL안에 저장 
// 사용후에 다시 POOL안에 반환 => 재사용 
// Connection 생성을 제한 => 연결시간을 줄일 수 있다 
// 웹 개발을 하는 업체는 DBCP를 사용한다 
// MyBatis / JPA의 기본은 DBCP
import java.util.*;
import java.sql.*;
import javax.sql.*; // 데이터베이스 정보 받아오기 => DataSource
import javax.naming.*; // Context => 이름으로 객체를 찾는다
public class FoodDAO {
	// 웹에는 상속이 거의 없다. 잘 안쓰는 추세. 포함 클래스로 사용한다 
	// has-a => 포함 클래스
	private Connection conn; // 오라클 연결 객체 
	// 오라클 서버와 연결하는 Socket
	
	private PreparedStatement ps; // 송수신 역할 => SQL을 전송, 결과값 ResultSet에 받기
	// OutputStream / BufferedReader 
	
	private static FoodDAO dao; // 싱글턴. 클라이언트 1명당 한개만 사용이 가능하게 만드는
	// 싱글턴 => 메모리 누수 방지 => DAO 에서 등장 
	// 미리 연결된 주소값 얻기 
	// 메소드는 한개의 기능 수행 => 재사용 하려는 목적, 반복되는 코드 제거
	// 메소드 여러개가 반복이 많은 경우 => 클래스 ==> jar (라이브러리)
	/*
		예약 기능
		---
		 게스트하우스 1
		 호텔 2
		 맛집 3
		 영화 4
		 ---------- 예약 한개의 테이블로 : 구분자(1,2..)로 구분 => 한개의 메소드로 처리 가능
	*/
	public void getConnection()
	{
		try
		{
			Context init=new InitialContext(); // 탐색기 열기 
			Context c=(Context)init.lookup("java://comp/env"); // 루트 폴더 
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		}catch(Exception ex) {}
	}
	// 반환 
	public void disConnection()
	{
		try
		
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// 싱글턴 
	public static FoodDAO newInstance()
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao; // 싱글턴 방식 
	}
	// ==> DAO 제작의 필수 사항 
	// 기능을 설정 
	public List<FoodVO> foodListData(int page)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			// 1. 주소값을 얻어 온다
			getConnection();
			// 2. SQL 문장을 만든다
			String sql="SELECT fno,name,poster,num "
					+ "FROM (SELECT fno,name,poster,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(food_location pk_food_location)*/fno,name,poster "
					+ "FROM food_location)) "
					+ "WHERE num BETWEEN ? AND ?"; // 페이징 인라인뷰
			ps=conn.prepareStatement(sql); // 오라클로 전송 
			int rowSize=12; // 1page => 1~12 , 2page => 13~24
			int start=(rowSize*page)-(rowSize-1); // rownum => 1번부터 시작
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			// 실행 요청 
			ResultSet rs=ps.executeQuery(); // 결과값 받아오기
			while(rs.next()) // 첫번째 ROW부터 읽어서 마지막까지 읽는다
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				String poster=rs.getString(3);
				poster=poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				list.add(vo);
			}
			rs.close();
			
		}catch(Exception ex)
		{
			// 어디서 에러 났는지 확인
			ex.printStackTrace();
		}
		finally
		{
			// 반환 => 재사용 (안쓰면 다른 사람이 사용 가능하게)
			disConnection();
		}
		return list;
	}
	// 총페이지 구하기 
	public int foodTotalPage()
	{
		int total=0;
		try
		{
			// 주소값 얻기
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM food_location";
			ps=conn.prepareStatement(sql); // 오라클로 전송 
			ResultSet rs=ps.executeQuery(); // 결과값 받아오기
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			// 반환 
			disConnection();
		}
		return total;
	}
	// 상세보기 (VO가 상세 하나에 대한 정보 다 갖고 있는 것) 
	public FoodVO foodDetailData(int fno) // fno => 사용자가 보내준 번호 
	{
		FoodVO vo=new FoodVO();
		try
		{
			getConnection(); // 상세보기니깐 SQL 문장 2번 날려야 한다 !
			String sql="UPDATE food_location SET "
					+ "hit=hit+1 "
					+ "WHERE fno="+fno; // 조회수 증가 SQL문장 
			ps=conn.prepareStatement(sql);
			// INSERT라 결과값 받을 게 없으니 ResultSet없이 바로 실행 
			// SELECT 는 읽어온 결과값 받아야 하니 ResultSet 사용 
			ps.executeUpdate(); // commit을 포함 => INSERT/UPDATE/DELETE
			// SELECT => 실행된 결과를 읽어 온다 => executeQuery()
			
			// 실제 데이터를 가지고 온다 (조회수 포함해서!)
			sql="SELECT fno,name,score,address,tel,poster,type,price,parking,menu,time "
					+ "FROM food_location "
					+ "WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setScore(rs.getDouble(3));
			vo.setAddress(rs.getString(4));
			vo.setPhone(rs.getString(5));
			vo.setPoster(rs.getString(6));
			vo.setType(rs.getString(7));
			vo.setPrice(rs.getString(8));
			vo.setParking(rs.getString(9));
			vo.setMenu(rs.getString(10));
			vo.setTime(rs.getString(11));
			
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
	
}




