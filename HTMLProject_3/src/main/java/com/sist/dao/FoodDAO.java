package com.sist.dao;
import java.util.*;
import java.sql.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	private static FoodDAO dao; // 싱글턴 만들 때 쓰는 방식 
	// 드라이버 등록 => 한번만 생성 (생성자에서 생성)
	public FoodDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 오라클 연결 
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {} // dbcp 에선 계정명,암호 숨길 수 있다
//		                     DBCP => 웹에서만 사용 가능, jdbc 대신 쓸 것
	}
	// 오라클 닫기 
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// 싱글턴 패턴 => 메모리 공간 1개 생성 
	public static FoodDAO newInstance()
	{
		// 라이브러리 => newInstance(),getInstance() =>싱글턴 
		if(dao==null) // 한번만 생성한다 
			dao=new FoodDAO();
		return dao;
	}
	// 기능 설정 
	// => 목록(table) => 인라인뷰 로 페이지 설정
	public List<FoodVO> foodListData(int page) // 목록 출력 => List
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		// FoodVO = ROW
		// https://www.menupan.com
		// /restaurant/restimg/001/zzmenuimg/e40054110_z.jpg
		// http://restaurant/restimg/001/zzmenuimg/e40054110_z.jpg
		try
		{
			getConnection();
			// 페이지마다 데이터 읽기
			int rowSize=20; // 한 페이지당 20개 출력 
			// num BETWEEN ? AND ? => 1page 1 ~ 20
			//                        2page 21 ~ 40
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			String sql="SELECT fno,poster,name,type,address,num "
					+ "FROM (SELECT fno,poster,name,type,address,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(food_menu_house fh_fno_pk)*/fno,poster,name,type,address "
					+ "FROM food_menu_house)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			// INDEX_ASC(테이블명 인덱스명,PK,UK) , INDEX_DESC() , INDEX()
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setPoster("https://www.menupan.com"+rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setType(rs.getString(4));
				vo.setAddress(rs.getString(5));
				
				list.add(vo);
				// => 프로젝트 80% => 직접 구현 
				// =>        20% (맛집정보,이미지..) => 크롤링
				
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	// 총페이지 
	public int foodTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return total;
	}
	// => 상세보기(table)
	
	
}




