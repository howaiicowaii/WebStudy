package com.sist.dao;
import java.util.*;
import java.sql.*;

import com.sist.dbcp.CreateDBCPconnection;
import com.sist.vo.*;
public class LoseCatDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPconnection dbconn
				=new CreateDBCPconnection();
	private static LoseCatDAO dao; 
	public static LoseCatDAO newInstance() // 싱글턴
	{
		if(dao==null)
			dao=new LoseCatDAO();
		return dao;
	}
	
	// 실종 고양이 목록
	public List<LoseCatVO> losecatListData(int page)
	{
		List<LoseCatVO> list=new ArrayList<LoseCatVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT lcno,cimage,ctitle,closeinfo,num "
					 + "FROM (SELECT lcno,cimage,ctitle,closeinfo,rownum as num "
					 + "FROM (SELECT lcno,cimage,ctitle,closeinfo "
					 + "FROM losecat ORDER BY hit DESC)) "
					 + "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-page);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				LoseCatVO vo=new LoseCatVO();
				vo.setLcno(rs.getInt(1));
				vo.setCimage(rs.getString(2));
				vo.setCtitle(rs.getString(3));
				vo.setCloseinfo(rs.getString(4));
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
	// 실종 고양이 총페이지
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
	// 실종 고양이 상세보기
	public LoseCatVO loseCatDetailData(int lcno)
	{
		LoseCatVO vo=new LoseCatVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="UPDATE losecat SET " // 조회수 증가
					 + "hit=hit+1 "
					 + "WHERE lcno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, lcno);
			ps.executeUpdate();
			
			sql="SELECT lcno,cimage,ctitle,cinfo,closeinfo,closedate,closeloc,cfeature "
					  + "FROM losecat "
					  + "WHERE lcno=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, lcno);
					ResultSet rs=ps.executeQuery();
					rs.next();
					vo.setLcno(rs.getInt(1));
					vo.setCimage(rs.getString(2));
					vo.setCtitle(rs.getString(3));
					vo.setCinfo(rs.getString(4));
					vo.setCloseinfo(rs.getString(5));
					vo.setClosedate(rs.getString(6));
					vo.setCloseloc(rs.getString(7));
					vo.setCfeature(rs.getString(8));
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
