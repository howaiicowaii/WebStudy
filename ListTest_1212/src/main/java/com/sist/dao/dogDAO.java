package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dbcp.*;
public class dogDAO {
	   private Connection conn;
	   private PreparedStatement ps;
	   private CreateDBCPConnection dbconn=
	            new CreateDBCPConnection();
	   private final int ROW_SIZE=12;
	   
	   public List<dogVO> dogListData(int page)
	   {
		   List<dogVO> list=new ArrayList<dogVO>();
		   try
		   {
			   conn=dbconn.getConnection();
			   String sql="SELECT ldno,image,title,num "
			   		+ "FROM (SELECT ldno,image,title,rownum as num "
			   		+ "FROM (SELECT ldno,image,title "
			   		+ "FROM loseani ORDER BY ldno)) "
			   		+ "WHERE num BETWEEN ? AND ?";
			   ps=conn.prepareStatement(sql);
			   int start=(ROW_SIZE*page)-(ROW_SIZE-1);
		       int end=ROW_SIZE*page;
		         
		       ps.setInt(1, start);
		       ps.setInt(2, end);
		       
		       ResultSet rs=ps.executeQuery();
		       while(rs.next())
		       {
		    	   dogVO vo=new dogVO();
		    	   vo.setLdno(rs.getInt(1));
		    	   vo.setImage(rs.getString(2));
		    	   vo.setTitle(rs.getString(3));
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
	   public int dogTotalPage()
	   {
		   int total=0;
		   try
		   {
			   conn=dbconn.getConnection();
			   String sql="SELECT CEIL(COUNT(*)/"+ROW_SIZE+") "
			   		+ "FROM loseani";
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
