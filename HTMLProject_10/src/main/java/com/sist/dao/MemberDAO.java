package com.sist.dao;
// 회원, 로그인 처리 
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
public class MemberDAO {
	   private Connection conn;
	   private PreparedStatement ps;
	   
	   private static MemberDAO dao;
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
	   public static MemberDAO newInstance()
	   {
	      if(dao==null)
	         dao=new MemberDAO();
	      return dao;
	   }
	   
	   // 기능 처리 
	   public MemberVO memberLogin(String id,String pwd)
	   {
		   MemberVO vo=new MemberVO();
		   try
		   {
			   // 1. 주소값 얻기 
			   getConnection();
			   // 2. SQL 문장 보내기
			   String sql="SELECT COUNT(*) FROM member_servlet "
			   		+ "WHERE id=?";
			   /*
			   	   로그인 처리 
			   	   1. id가 존재하는지 확인 => COUNT(*) 가 0이면 존재하지 않는 것 
			   	   2. id(존재X) => 종료 => NOID
			   	      id(존재O)
			   	      3. PWD를 확인
			   	         pwd(안맞는경우) => 종료 => NOPWD
			   	         pwd(맞는경우) => 종료 => OK => 화면 이동 (MainServlet)
			   	         ========================
			   	          개인 정보중에 프로그램 종료시까지 
			   	          유지해야 되는 데이터를 세션에 저장
			   	           세션 => 모든 서블릿/JSP에서 필요시마다 사용 가능
			   	           === 전역변수
			   	       세션 / 쿠키 => 저장공간
			   	        |      | 쿠키:브라우저에 저장 (보안 취약)
			   	세션:서버에 저장 (보안 좋다)
			   	       1) 저장 가능
			   	       2) 수정 가능
			   	       3) 일부 정보 삭제 가능
			   	       4) 모든 정보 삭제 가능
			   	       5) 저장기간 설정 가능
			   	          
			   */
			   ps=conn.prepareStatement(sql);
			   ps.setString(1, id);
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   int count=rs.getInt(1);
			   rs.close();
			   if(count==0) // id가 없는 상태
			   {
				   vo.setMsg("NOID");
			   }
			   else // id가 있는 상태
			   {
				   sql="SELECT pwd,name FROM member_servlet "
				   		+ "WHERE id=?";
				   ps=conn.prepareStatement(sql);
				   ps.setString(1, id); // 1번 물음표에 id 넣기
				   rs=ps.executeQuery();
				   rs.next();
				   String db_pwd=rs.getString(1);
				   String name=rs.getString(2);
				   rs.close();
				   if(db_pwd.equals(pwd))
				   {
					   vo.setMsg("OK"); // pwd 같으니 로그인 OK
					   vo.setName(name); // 세션에 name을 저장할 용도(댓글엔 id가 아닌 name이 올라간다)
				   }
				   else
				   {
					   vo.setMsg("NOPWD"); // pwd 다르다
				   }
			   }
		   }catch(Exception ex)
		   {
			   ex.printStackTrace();
		   }
		   finally
		   {
			   // 반환
			   disConnection();
		   }
		   return vo;
	   }
}
