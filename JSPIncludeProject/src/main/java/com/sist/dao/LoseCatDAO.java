package com.sist.dao;
import java.util.*;

import com.sist.dbcp.CreateDBCPConnection;
import com.sist.vo.LoseCatVO;

import java.sql.*;
public class LoseCatDAO {
	private Connection conn; // 데이터베이스 연결
	private PreparedStatement ps; // SQL 전송 후 결과값 받는
	private static LoseCatDAO dao; // 싱글턴
	private CreateDBCPConnection dbconn= // DBCP 라이브러리 읽어오기
			new CreateDBCPConnection();
	
	// 1. 기능 : 리스트 (목록)
	public List<LoseCatVO> loseCatListData(int page)
	{
		List<LoseCatVO> list=new ArrayList<LoseCatVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT lcno,ctitle,cimage,num "
					 + "FROM (SELECT lcno,ctitle,cimage,rownum as num "
					 + "FROM (SELECT lcno,ctitle,cimage "
					 + "FROM losecat ORDER BY lcno ASC)) "
					 + "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			// 실행 요청전에 ? 값들 채워주기
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				LoseCatVO vo=new LoseCatVO();
				vo.setLcno(rs.getInt(1));
				vo.setCtitle(rs.getString(2));
				vo.setCimage(rs.getString(3));
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
	// 기능 : 총페이지
	public int loseCatTotalPage()
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM losecat";
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
			dbconn.disConnection(conn, ps);
		}
		return total;
	}
}
