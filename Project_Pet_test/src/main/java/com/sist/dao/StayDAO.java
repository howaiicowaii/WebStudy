package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
import com.sist.dbcp.*;

public class StayDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateDBCPconnection dbconn=new CreateDBCPconnection();
	private static StayDAO dao;
	private final int ROW_SIZE=12;
	
	// 싱글턴
		public static StayDAO newInstance() {
			if(dao==null)
				dao=new StayDAO();
			return dao;
		}
	// 목록출력
		public List<StayVO> StayListData(int page){
			List<StayVO> list=new ArrayList<StayVO>();
			try {
				conn=dbconn.getConnection();
				// num BETWEEN ? AND ? => 1page 1~20
				//						  2page 21~40
				//							... 
				int start=(ROW_SIZE*page)-(ROW_SIZE-1);
				int end=ROW_SIZE*page;
				String sql="SELECT stay_no,stype,sname,score,address,price,mainimage,num "
						+ "FROM STAYIMAGE,(SELECT stay_no,stype,sname,score,address,price,rownum as num "
						+ "FROM (SELECT /*+ INDEX_ASC(stayinfo stayinfo_stay_no_pk)*/stay_no,stype,sname,score,address,price,mainimage "
						+ "FROM stayinfo,STAYIMAGE WHERE STAYIMAGE.SINO=stayinfo.STAY_NO)) "
						+ "WHERE (STAYIMAGE.SINO=STAY_NO) "
						+ "AND (num BETWEEN ? AND ?)";
				// 실무에서는 위처럼 order by를 쓰지 않고 index_asc를 쓴다
				ps=conn.prepareStatement(sql);
				ps.setInt(1, start);
				ps.setInt(2, end);
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					StayVO vo=new StayVO();
					vo.setStayno(rs.getInt(1));
					vo.setType(rs.getString(2));
					vo.setName(rs.getString(3));
					vo.setScore(rs.getDouble(4));
					vo.setAddress(rs.getString(5));
					vo.setPrice(rs.getInt(6));
					vo.setImage(rs.getString(7));
					
					list.add(vo);
					// 직접구현 => 80%
					// 20% => 정보,이미지
				}
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				dbconn.disConnection(conn, ps);
			}
			return list;
		}
		public int StayTotalPage() {
			int total=0;
			try {
				conn=dbconn.getConnection();
				String sql="SELECT CEIL(COUNT(*)/"+ROW_SIZE+") FROM stayinfo";
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				total=rs.getInt(1);
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				dbconn.disConnection(conn, ps);
			}
			return total;
		}
		
		// 상세보기
		public StayVO stayDetail(int sno) {
			StayVO vo=new StayVO();
			try {
				conn=dbconn.getConnection();
				String sql="UPDATE stayinfo SET "
						+ "hit=hit+1 "
						+ "WHERE stay_no="+sno;
				ps=conn.prepareStatement(sql);
				ps.executeUpdate();
				
				sql="SELECT stype,sname,score,address,detail_address,price,review_count,around,basic,petinfo,other,mainimage,sub1,sub2,sub3,sub4 FROM stayinfo,STAYDETAIL,stayimage "
						+ "WHERE stay_no=sdno AND stay_no=sino AND stay_no=?";
				// 실무에서는 위처럼 order by를 쓰지 않고 index_asc를 쓴다
				ps=conn.prepareStatement(sql);
				ps.setInt(1, sno);
				// INDEX_ASC(테이블명 인덱스명,PK,UK), INDEX_DESC(), INDEX()
				ResultSet rs=ps.executeQuery();
				rs.next();
					
					vo.setType(rs.getString(1));
					vo.setName(rs.getString(2));
					vo.setScore(rs.getDouble(3));
					vo.setAddress(rs.getString(4));
					vo.setDetailaddr(rs.getString(5));
					vo.setPrice(rs.getInt(6));
					vo.setRevcount(rs.getInt(7));
					vo.setAround(rs.getString(8));
					vo.setBasic(rs.getString(9));
					vo.setPetinfo(rs.getString(10));
					vo.setOther(rs.getString(11));
					vo.setImage(rs.getString(12));
					vo.setSub1(rs.getString(13));
					vo.setSub2(rs.getString(14));
					vo.setSub3(rs.getString(15));
					vo.setSub4(rs.getString(16));
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				dbconn.disConnection(conn, ps);
			}
			return vo;
		}
		
		public List<StayVO> stayLikeTop() {
			List<StayVO> list=new ArrayList<StayVO>();
			int top=6;
			try {
				conn=dbconn.getConnection();
				String sql="SELECT stay_no,sname,score,address,price,jjim,likecount,mainimage,num "
						+ "FROM (SELECT stay_no,sname,score,address,price,jjim,likecount,mainimage,rownum AS num "
						+ "FROM (SELECT stay_no,sname,score,address,price,jjim,likecount,mainimage "
						+ "FROM stayinfo,stayimage WHERE stayinfo.STAY_NO=stayimage.SINO ORDER BY likecount DESC,STAY_NO ASC)) "
						+ "WHERE num<="+top;
				
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					StayVO vo=new StayVO();
					vo.setStayno(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setScore(rs.getDouble(3));
					vo.setAddress(rs.getString(4));
					vo.setPrice(rs.getInt(5));
					vo.setJjim(rs.getInt(6));
					vo.setLike(rs.getInt(7));
					vo.setImage(rs.getString(8));
					list.add(vo);
				}
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				dbconn.disConnection(conn, ps);
			}
			return list;
		}
		
		public List<StayVO> stayAddressListData(String fd){
			List<StayVO> list=new ArrayList<StayVO>();
			try {
				conn=dbconn.getConnection();
				String sql="SELECT stay_no,sname,score,address,detail_address,price,mainimage,rownum "
						+ "FROM (SELECT stay_no,sname,score,address,detail_address,price,mainimage "
						+ "FROM stayinfo,stayimage WHERE stayinfo.STAY_NO=stayimage.SINO) "
						+ "WHERE detail_address LIKE '%'||?||'%' AND rownum<9";
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, fd);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					StayVO vo=new StayVO();
					vo.setStayno(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setScore(rs.getDouble(3));
					vo.setAddress(rs.getString(4));
					vo.setDetailaddr(rs.getString(5));
					vo.setPrice(rs.getInt(6));
					vo.setImage(rs.getString(7));
					list.add(vo);
				}
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				dbconn.disConnection(conn, ps);
			}
			return list;
		}
		
		
}
