package com.sist.dao;
import java.util.*;

import com.sist.dao.keepAnimal2VO;
import com.sist.dao.loseCatVO;

import java.sql.*;
public class loseCatDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	private static loseCatDAO dao; // 싱글턴 만들 때 쓰는 방식 
	// 드라이버 등록 => 한번만 생성 (생성자에서 생성)
	public loseCatDAO()
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
	public static loseCatDAO newInstance()
	{
		// 라이브러리 => newInstance(),getInstance() =>싱글턴 
		if(dao==null) // 한번만 생성한다 
			dao=new loseCatDAO();
		return dao;
	}
	// 기능 설정 
	// => 목록(table) => 인라인뷰 로 페이지 설정
	public List<loseCatVO> loseCatListData(int page)
	{
		List<loseCatVO> list=new ArrayList<loseCatVO>();
		// FoodVO = ROW
		try
		{
			getConnection();
			// 페이지마다 데이터 읽기
			int rowSize=10;// 한 페이지당 10개 출력 
			// num BETWEEN ? AND ? => 1page 1 ~ 20
			//                        2page 21 ~ 40
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
//			String sql="SELECT aano,sub,image,content,num "
//					+ "FROM (SELECT aano,sub,image,content,rownum as num "
//					+ "FROM (SELECT /*+ INDEX_ASC(loseAni adoptAnimal_aano_pk)*/aano,sub,image,content "
//					+ "WHERE num BETWEEN ? AND ?";
			String sql="SELECT lcno,cimage,ctitle,cinfo,"
					+ "cLoseInfo,cLoseDate,cLoseLoc,cFeature "
					+ "FROM loseCat";
//			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			// INDEX_ASC(테이블명 인덱스명,PK,UK) , INDEX_DESC() , INDEX()
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				loseCatVO vo=new loseCatVO();
				vo.setLcno(rs.getInt(1));
				vo.setCimage(rs.getString(2));
				vo.setCtitle(rs.getString(3));
				vo.setCinfo(rs.getString(4));
				vo.setcLoseInfo(rs.getString(5));
				vo.setcLoseDate(rs.getString(6));
				vo.setcLoseLoc(rs.getString(7));
				vo.setcFeature(rs.getString(8));
				
				list.add(vo);
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
	public int loseCatTotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM loseCat";
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
	public void loseCatInsert(loseCatVO vo)
	{
		try
		{
		getConnection();
		String sql="INSERT INTO loseCat VALUES(loseCat_lcno_seq.nextval,?,?,?,?,?,?,?)";
		ps=conn.prepareStatement(sql);
		ps.setString(1, vo.getCimage());
		ps.setString(2, vo.getCtitle());
		ps.setString(3, vo.getCinfo());
		ps.setString(4, vo.getcLoseInfo());
		ps.setString(5, vo.getcLoseDate());
		ps.setString(6, vo.getcLoseLoc());
		ps.setString(7, vo.getcFeature());

		ps.executeUpdate();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 상세보기(table)
	
}