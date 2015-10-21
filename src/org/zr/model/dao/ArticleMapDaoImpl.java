package org.zr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.util.JDBCUtils_c3p0;



public class ArticleMapDaoImpl implements ArticleMapDao {
private static Logger  log=LoggerFactory.getLogger(ArticleMapDaoImpl.class);
	@Override
	public String findByContent(String content) {
		String sql="select * from ArticleMap where context like ? ";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String mid="k";
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+content+"%");
			rs=ps.executeQuery();
		  while(rs.next()){
				mid=rs.getString("mid");
			}
		} catch (Exception e) {
			log.info("查询多图文消息失败！！"+e.getLocalizedMessage());
			e.printStackTrace();
		} finally{
			JDBCUtils_c3p0.release(conn, ps, rs);
		}
		
		return mid;
	}

	@Override
	public void saveArticleMap(String mid, String content) {
		String sql="insert into ArticleMap (mid,context) values (?,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		//ResultSet rs=null;
		
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, content);
			ps.executeUpdate();
						
		} catch (Exception e) {
			log.info("存储多图文消息map失败！！"+e.getLocalizedMessage());
			e.printStackTrace();
		} finally{
			JDBCUtils_c3p0.release(conn, ps, null);
		}
		
		
	}

	@Override
	public List<String> findAllMegMap() {
	List<String> list=new ArrayList<String>();
	String sql="select * from ArticleMap";
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String mid="";
	String content="";
	String id="";
	try {
		conn=ConnectToDatabase.GetConnection();
		ps=conn.prepareStatement(sql);
	
		rs=ps.executeQuery();
	  while(rs.next()){
			mid=rs.getString("mid");
			content=rs.getString("context");
			id=rs.getString("id");
			String a=mid+"@"+content+"@"+id;
			list.add(a);
		}
	} catch (Exception e) {
		log.info("查询多图文消息map失败！！"+e.getLocalizedMessage());
		e.printStackTrace();
	} finally{
		JDBCUtils_c3p0.release(conn, ps, rs);
	}
	
	return list;
		
	}

	@Override
	public void UpdateByid(String id, String mid, String context) {
		
		String sql="update  ArticleMap set mid=?,context=? where id=?";
		Connection conn=null;
		PreparedStatement ps=null;
		//ResultSet rs=null;
		
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, context);
			ps.setString(3, id);
			ps.executeUpdate();
						
		} catch (Exception e) {
			log.info("更新多图文消息map失败！！"+e.getLocalizedMessage());
			e.printStackTrace();
		} finally{
			JDBCUtils_c3p0.release(conn, ps, null);
		}
	}

}
