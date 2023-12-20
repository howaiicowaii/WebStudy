package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.dbcp.CreateDBCPconnection;
import com.sist.vo.ProductVO;

public class ProductDAO {
private Connection conn;
private PreparedStatement ps;
private CreateDBCPconnection dbconn=new CreateDBCPconnection();
private static ProductDAO dao;


public static ProductDAO newInstance() {
	if (dao==null) {
		dao=new ProductDAO();
	}
	return dao;
}


public List<ProductVO> homeProduct(){
	List<ProductVO>list=new ArrayList<>();
	try {
		conn=dbconn.getConnection();
		String sql="SELECT p_image,p_name,num "
					+"FROM (SELECT p_image,p_name,rownum as num "
					+"FROM (SELECT p_image,p_name FROM product_detail ORDER BY p_hit DESC)) "
					+"WHERE num BETWEEN 1 AND 5";
		ps=conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			ProductVO vo=new ProductVO();
		String p_image=rs.getString(1);
		String p_name=rs.getString(2);
	
		
		vo.setP_image(p_image);
		vo.setP_name(p_name);
		list.add(vo);
		
		
		}
		rs.close();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	finally {
		dbconn.disConnection(conn, ps);
	}
	
	return list;
}

	
	
}
