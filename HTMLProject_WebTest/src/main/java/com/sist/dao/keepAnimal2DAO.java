package com.sist.dao;
import java.util.*;

import com.sist.dao.keepAnimal2VO;

import java.sql.*;
public class keepAnimal2DAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	private static keepAnimal2DAO dao; // 싱글턴 만들 때 쓰는 방식 
	// 드라이버 등록 => 한번만 생성 (생성자에서 생성)
	public keepAnimal2DAO()
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
	public static keepAnimal2DAO newInstance()
	{
		// 라이브러리 => newInstance(),getInstance() =>싱글턴 
		if(dao==null) // 한번만 생성한다 
			dao=new keepAnimal2DAO();
		return dao;
	}
	// 기능 설정 
	// => 목록(table) => 인라인뷰 로 페이지 설정
	public List<keepAnimal2VO> keepAnimal2ListData(int page)
	{
		List<keepAnimal2VO> list=new ArrayList<keepAnimal2VO>();
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
			String sql="SELECT kano,keepLoc,keepTitle,keepWriter,"
					+ "keepRegDate,keepFoundLoc,keepImage,keepContent "
					+ "FROM keepAnimal2";
//			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			// INDEX_ASC(테이블명 인덱스명,PK,UK) , INDEX_DESC() , INDEX()
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				keepAnimal2VO vo=new keepAnimal2VO();
				vo.setKano(rs.getInt(1));
				vo.setKeepLoc(rs.getString(2));
				vo.setKeepTitle(rs.getString(3));
				vo.setKeepWriter(rs.getString(4));
				vo.setKeepRegDate(rs.getString(5));
				vo.setKeepFoundLoc(rs.getString(6));
				vo.setKeepImage(rs.getString(7));
				vo.setKeepContent(rs.getString(8));
				
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
	public int keepAnimal2TotalPage()
	{
		int total=0;
		try
		{
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM keepAnimal2";
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
	public void keepAnimal2Insert(keepAnimal2VO vo)
	{
		try
		{
		getConnection();
		String sql="INSERT INTO keepAnimal2 VALUES(keepAnimal2_kano_seq.nextval,?,?,?,?,?,?,?)";
		ps=conn.prepareStatement(sql);
		ps.setString(1, vo.getKeepLoc());
		ps.setString(2, vo.getKeepTitle());
		ps.setString(3, vo.getKeepWriter());
		ps.setString(4, vo.getKeepRegDate());
		ps.setString(5, vo.getKeepFoundLoc());
		ps.setString(6, vo.getKeepImage());
		ps.setString(7, vo.getKeepContent());

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