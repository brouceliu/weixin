package org.zr.model.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.message.zr.response.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.util.JDBCUtils_c3p0;

public class ArticleDaoImpl implements ArticleDao{
	private static Logger log = LoggerFactory.getLogger(ArticleDaoImpl.class);
	@Override
	public void savePicMeg(Article atcle, String mid) {
		String sql="insert into article (picurl,url,description,mid,title) values(?,?,?,?,?)";
		PreparedStatement ps=null;
		log.info("save"+mid);
		Connection conn=null;
		try {
		    conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, atcle.getPicUrl());
			ps.setString(2, atcle.getUrl());
			ps.setString(3, atcle.getDescription());
			ps.setString(4, mid);
			ps.setString(5, atcle.getTitle());
			ps.executeUpdate();
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			log.info("存储图文消息失败"+sw.toString());
			
			e.printStackTrace();
		}finally{
			JDBCUtils_c3p0.release(conn, ps, null);
		}
	}

	@Override
	public List<Article> findByMid(String mid) {
		List<Article> list=new ArrayList<Article>();
		
		String sql="select * from article where mid=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs=ps.executeQuery();
			while(rs.next()){
				Article artcile=new Article();
				artcile.setDescription(rs.getString("description"));
				artcile.setPicUrl(rs.getString("picurl"));
				artcile.setTitle(rs.getString("title"));
				artcile.setUrl(rs.getString("url"));
				list.add(artcile);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			log.info("查询图文消息失败"+sw.toString());
			
			e.printStackTrace();
		} finally{
			JDBCUtils_c3p0.release(conn, ps, null);
		}
		
		return list;
	}

	@Override
	public List<Article> findAll() {
List<Article> list=new ArrayList<Article>();
		
		String sql="select * from article";
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
		
			rs=ps.executeQuery();
			while(rs.next()){
				Article artcile=new Article();
				artcile.setDescription(rs.getString("description"));
				artcile.setPicUrl(rs.getString("picurl"));
				artcile.setTitle(rs.getString("title")+"@"+rs.getString("mid")+"@"+rs.getShort("id"));
				artcile.setUrl(rs.getString("url"));
				list.add(artcile);
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			log.info("查询all图文消息失败"+sw.toString());
			
			e.printStackTrace();
		} finally{
			JDBCUtils_c3p0.release(conn, ps, null);
		}
		
		return list;
		
		
		
	}

	@Override
	public void upDatePicMeg(String id, Article art,String mid) {
		log.info("update id is"+id);
		String sql="update  article set picurl=?,url=?,description=?,title=?,mid=? where id=?";
		PreparedStatement ps=null;
		Connection conn=null;
		try {
		    conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, art.getPicUrl());
			ps.setString(2, art.getUrl());
			ps.setString(3, art.getDescription());
			ps.setString(4, art.getTitle());
			ps.setString(5, mid);
			ps.setString(6, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			log.info("更新图文消息失败"+sw.toString());
			
			e.printStackTrace();
		}finally{
			JDBCUtils_c3p0.release(conn, ps, null);
		}
	}

}
