package com.sist.dao;
import java.util.*;

import com.sist.dbcp.CreateDBCPconnection;
import com.sist.vo.AnimalVO;

import java.sql.*;
public class AnimalDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPconnection dbconn=
			new CreateDBCPconnection();
	private static AnimalDAO dao;
	
	public static AnimalDAO newInstance() // 싱글턴
	{
		if(dao==null)
			dao=new AnimalDAO();
		return dao;
	}
	
	// 메인에 띄울 보호중인 동물. 번호,이미지,발견장소,작성일,제목,내용,조회수
	public List<AnimalVO> animalBlogList()
	{
		List<AnimalVO> list=new ArrayList<AnimalVO>();
		try
		{
			conn=dbconn.getConnection();
//			String sql="SELECT kano,keepimage,keepfoundloc,keepregdate,"
//					+ "keeptitle,keepcontent,hit "
//					+ "FROM keepanimal2 ORDER BY hit DESC";
			String sql="SELECT kano,keepimage,keepfoundloc,keepregdate,keeptitle,keepcontent,num "
					+ "FROM (SELECT kano,keepimage,keepfoundloc,keepregdate,keeptitle,keepcontent,rownum as num "
					+ "FROM (SELECT kano,keepimage,keepfoundloc,keepregdate,keeptitle,keepcontent "
					+ "FROM keepanimal2 ORDER BY kano ASC)) "
					+ "WHERE num BETWEEN 1 AND 3";
			ps=conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			

			while(rs.next())
			{
			AnimalVO vo=new AnimalVO();
			vo.setKano(rs.getInt(1));
			vo.setKeepimage(rs.getString(2));
			vo.setKeepfoundloc(rs.getString(3));
			vo.setKeepregdate(rs.getString(4));
			vo.setKeeptitle(rs.getString(5));
			vo.setKeepcontent(rs.getString(6));
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
	
	// 실종 강아지 목록 
	// 실종 고양이 목록
	// 보호중인 동물 목록
	public List<AnimalVO> animalListData(int page)
	{
		List<AnimalVO> list=new ArrayList<AnimalVO>();
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT kano,keepimage,keepregdate,keeptitle,keepfoundloc,num "
					 + "FROM (SELECT kano,keepimage,keepregdate,keeptitle,keepfoundloc,rownum as num "
					 + "FROM (SELECT kano,keepimage,keepregdate,keeptitle,keepfoundloc "
					 + "FROM keepanimal2 ORDER BY kano ASC)) "
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
				AnimalVO vo=new AnimalVO();
				vo.setKano(rs.getInt(1));
				vo.setKeepimage(rs.getString(2));
				vo.setKeepregdate(rs.getString(3));
				vo.setKeeptitle(rs.getString(4));
				vo.setKeepfoundloc(rs.getString(5));
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
	public int animalTotalPage()
	{
		int total=0;
		try
		{
			conn=dbconn.getConnection();
			String sql="SELECT CEIL(COUNT(*)/12.0) FROM keepanimal2";
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
	public AnimalVO animalDetailData(int kano)
	{
		AnimalVO vo=new AnimalVO();
		try
		{
			conn=dbconn.getConnection();
			String sql="UPDATE keepanimal2 SET " // 조회수 증가
					 + "hit=hit+1 "
					 + "WHERE kano="+kano;
			sql="SELECT kano,keeploc,keeptitle,keepfoundloc,keepwriter,keepregdate,keepimage,keepcontent "
			  + "FROM keepanimal2 "
			  + "WHERE kano="+kano;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			ResultSet rs=ps.executeQuery();
			vo.setKano(rs.getInt(1));
			vo.setKeeploc(rs.getString(2));
			vo.setKeeptitle(rs.getString(3));
			vo.setKeepfoundloc(rs.getString(4));
			vo.setKeepwriter(rs.getString(5));
			vo.setKeepregdate(rs.getString(6));
			vo.setKeepimage(rs.getString(7));
			vo.setKeepcontent(rs.getString(8));
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
