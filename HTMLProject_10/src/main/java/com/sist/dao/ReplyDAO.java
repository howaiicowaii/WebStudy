package com.sist.dao;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
public class ReplyDAO {
	// 로그인이 되어야만 댓글 쓸 수 있게 만들 것
	   private Connection conn;
	   private PreparedStatement ps;
	   
	   private static ReplyDAO dao;
	   public void getConnection()	
	   {
	      try
	      {
	         Context init=new InitialContext();
	         Context c=(Context)init.lookup("java://comp/env");
	         DataSource ds=(DataSource)c.lookup("jdbc/oracle");
	         conn=ds.getConnection();
	      }catch(Exception ex) {}
	   }
	   public void disConnection()
	   {
	      try
	      {
	         if(ps!=null) ps.close();
	         if(conn!=null) conn.close();
	      }catch(Exception ex) {}
	   }
	   public static ReplyDAO newInstance()
	   {
	      if(dao==null)
	         dao=new ReplyDAO();
	      return dao;
	   }
	   
	   // 기능 (CRUD)
	   // 1. 목록 => typeno : 1(GoodsAll), 2 3 4  
	   public List<ReplyVO> replyListData(int typeno,int gno) // gno = 상품번호 다 겹치기 때문에 typeno로 구분
	   {
		   List<ReplyVO> list=new ArrayList<ReplyVO>();
		   try
		   {
			   getConnection();
			   String sql="SELECT rno,id,name,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
			   		+ "FROM reply_serv "
			   		+ "WHERE typeno=? AND gno=?"
			   		+ "ORDER BY rno DESC"; // 그냥 regdate 하면 시간대를 못가져온다
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, typeno); // 1번 물음표에 typeno
			   ps.setInt(2, gno); // 2번 물음표에 gno
			   ResultSet rs=ps.executeQuery();
			   while(rs.next())
			   {
				   ReplyVO vo=new ReplyVO();
				   vo.setRno(rs.getInt(1));
				   vo.setId(rs.getString(2));
				   vo.setName(rs.getString(3));
				   vo.setMsg(rs.getString(4));
				   vo.setDbday(rs.getString(5)); // regdate 를 Dbday 로 문자열로 받기
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
	   // 2. 댓글 
	   /*
	      RNO 
	      TY
	   */
	   public void replyInsert(ReplyVO vo)
	   {
		   try
		   {
			   getConnection();
			   String sql="INSERT INTO reply_serv VALUES("
			   		+ "reply_serv_rno_seq.nextval,"
			   		+ "?,?,?,?,?,SYSDATE)";
			   ps=conn.prepareStatement(sql);
			   ps.setInt(1, vo.getTypeno()); // 1번 물음표에 
			   ps.setInt(2, vo.getGno());
			   ps.setString(3, vo.getId());
			   ps.setString(4, vo.getName());
			   ps.setString(5, vo.getMsg());
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
	   // 3. 수정 => Jquery
	   public void replyUpdate(int rno,String msg)
	   {
		   try
		   {
			   getConnection();
			   String sql="UPDATE reply_serv SET "
			   		+ "msg=? "
			   		+ "WHERE rno=?";
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, msg); // 1번 물음표에 msg
			   ps.setInt(2, rno); // 2번 물음표에 rno
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
	   // 4. 삭제
	   public void replyDelete(int rno)
	   {
		   try
		   {
			   getConnection();
			   String sql="DELETE FROM reply_serv "
			   		+ "WHERE rno="+rno;
			   ps=conn.prepareStatement(sql);
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
	   
	   
}




