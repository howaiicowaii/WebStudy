package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.dbcp.*; // connection 등 넣어놓은 라이브러리
import com.sist.vo.MemberVO;
public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps; 
	private CreateDBCPConnection dbconn=
			new CreateDBCPConnection();
	private static MemberDAO dao; // 싱글턴 
	
	// 1.  로그인
	public MemberVO isLogin(String id,String pwd)
	{
		// Spring 에서 변경 => 암호화/복호화
		MemberVO vo=new MemberVO();
		try
		{
			conn=dbconn.getConnection();
			// dbconn.getConnection() => this.conn은 null 이라고 오류발생
			// id 존재여부 확인
			String sql="SELECT COUNT(*) FROM jspMember "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id); // 'hong'
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			// pwd 확인
			if(count==0) // ID가 없는 상태
			{
				vo.setMsg("NOID");
			}
			else // ID가 있는 상태 
			{
				// session에 저장할 데이터 가져와야 한다
				sql="SELECT id,name,admin,pwd "
				  + "FROM jspMember "
				  + "WHERE id=?"; // 문자열 들어가면 ? 로 처리
				// "WHERE id="+id => 오류 발생
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				String db_id=rs.getString(1);
				String name=rs.getString(2);
				String admin=rs.getString(3);
				String db_pwd=rs.getString(4);
				
				if(db_pwd.equals(pwd)) // 로그인 된 상태
				{
					vo.setMsg("OK"); // session 에 저장
					vo.setId(db_id);
					vo.setName(name);
					vo.setAdmin(admin);
				}
				else // 로그인 안된 상태 
				{
					vo.setMsg("NOPWD");
				}
				rs.close();
			}
			
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
