package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.dbcp.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPConnection dbconn=new CreateDBCPConnection();
	private static FoodDAO dao;
	public static FoodDAO newInstance() // 싱글턴
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	// 목록 
	public List<FoodVO> foodListData(int page,String fd,String ss) 
	                                 // fd => find (검색) / ss => 검색어 // fd => field data / ss => search string
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT fno,poster,name,address,num "
		            +"FROM (select fno,poster,name,address,rownum as num "
		            +"FROM (select fno,poster,name,address "
		            +"FROM food_menu_house  "
		            +"WHERE "+fd+" LIKE '%'||?||'%' "
		            +"ORDER BY fno ASC)) "
		            +"WHERE num BETWEEN ? AND ?";

			// 컬럼명 / 테이블명은 ? 로 처리할 수 없다
			// ps.setString(1,fd) => fd가 address라 하면 => 'address' 이렇게 따옴표 붙어서 오류 발생. so, +fd+ 로 넣어준다
			// MyBatis => ${} #{}
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setString(1, ss);
			// fd => field data / ss => search string
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setPoster(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setAddress(rs.getString(4));
				
				list.add(vo);
			}
			rs.close();
			
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return list;
	}
	
	// 총페이지
	public int foodTotalPage(String fd,String ss)
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) "
					+ "FROM food_menu_house "
					+ "WHERE "+fd+" LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
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
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
	/*
		=> 1. 데이터베이스 => 어떤 값(요청)
		=> 2. 화면 어떤 데이터가 필요한지 ...
		
		=> ajax / MVC
		
		자바
		 1. 코딩 => 반복하고 있다? => 메소드 로 반복제거. 메소드 여러개가 있다? => 클래스
		    => 재사용 (목적)
		 2. 메소드
		    ===== 1. 요청값 / 2. 결과값
		오라클 공부
		 1. SQL문장 자주 써보기 => JOIN, SUBQUERY
		 
		JSP => 연결
		================================
		| 체계적 : 스프링
		
	*/
	// 상세보기 
	public FoodVO foodDetailData(int fno)
	{
		// ss / fd => 화면 이동할 때 사용하는 변수
		FoodVO vo=new FoodVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT fno,poster,name,type,address,phone,"
					+ "score,theme,price,time,seat,content "
					+ "FROM food_menu_house "
					+ "WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setPoster(rs.getString(2));
			vo.setName(rs.getString(3));
			vo.setType(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setPhone(rs.getString(6));
			vo.setScore(rs.getDouble(7));
			vo.setTheme(rs.getString(8));
			vo.setPrice(rs.getString(9));
			vo.setTime(rs.getString(10));
			vo.setSeat(rs.getString(11));
			vo.setContent(rs.getString(12));
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			dbconn.disConnection(conn, ps);
		}
		return vo;
	}
	
}


