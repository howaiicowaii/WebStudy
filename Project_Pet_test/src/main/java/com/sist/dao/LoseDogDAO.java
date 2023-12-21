package com.sist.dao;
import java.util.*;

import com.sist.dbcp.CreateDBCPconnection;
import com.sist.vo.LoseDogVO;

import java.sql.*;
public class LoseDogDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPconnection dbconn=
			new CreateDBCPconnection();
	private static LoseDogDAO dao;
	public static LoseDogDAO newInstance() // 싱글턴 
	{
		if(dao==null)
			dao=new LoseDogDAO();
		return dao;
	}
	// 실종 강아지 목록 
	public List<LoseDogVO> loseDogListData(int page)
	{
		List<LoseDogVO> list=new ArrayList<LoseDogVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT ldno,image,title,loseinfo,num "
					 + "FROM (SELECT ldno,image,title,loseinfo,rownum as num "
					 + "FROM (SELECT ldno,image,title,loseinfo "
					 + "FROM loseani ORDER BY hit DESC)) "
					 + "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=12;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				LoseDogVO vo=new LoseDogVO();
				vo.setLdno(rs.getInt(1));
				vo.setImage(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setLoseinfo(rs.getString(4));
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
	// 실종 강아지 총페이지
	public int loseDogTotalPage()
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM loseani";
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
	// 실종 강아지 상세보기
	public LoseDogVO loseDogDetailData(int ldno)
	{
		LoseDogVO vo=new LoseDogVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="UPDATE loseani SET " // 조회수 증가
					 + "hit=hit+1 "
					 + "WHERE ldno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ldno);
			ps.executeUpdate();
			sql="SELECT ldno,image,title,info,loseinfo,losedate,loseloc,feature "
			  + "FROM loseani "
			  + "WHERE ldno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, ldno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setLdno(rs.getInt(1));
			vo.setImage(rs.getString(2));
			vo.setTitle(rs.getString(3));
			vo.setInfo(rs.getString(4));
			vo.setLoseinfo(rs.getString(5));
			vo.setLosedate(rs.getString(6));
			vo.setLoseloc(rs.getString(7));
			vo.setFeature(rs.getString(8));
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
