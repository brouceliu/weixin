package org.zr.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.Knowledge;
import org.zr.util.JDBCUtils_c3p0;

public class ChatDaoImpl implements ChatDao {
	private static Logger log = LoggerFactory.getLogger(ChatDaoImpl.class);
	@Override
	public List<Knowledge> findAllKnowledge() {
		List<Knowledge> knowlist=new ArrayList<Knowledge>();
		try {
			Connection conn=ConnectToDatabase.GetConnection();
		
			String sql="select * from knowledge";
			PreparedStatement ps;
			ResultSet rs;
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Knowledge kg=new Knowledge();
				kg.setId(rs.getInt("id"));
				kg.setQuestion(rs.getString("question"));
				kg.setAnswer(rs.getString("answer"));
				kg.setCategory(rs.getInt("category"));
				knowlist.add(kg);
				
				
			}
			JDBCUtils_c3p0.release(conn, ps, rs);
			
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return knowlist;
	}

	@Override
	public int getLastCategory(String openid) {
		int chatCategory=-1;
		String sql="select chat_category from chat_log where open_id=? order by id desclimit 0,1";
		
		try {
			Connection conn=ConnectToDatabase.GetConnection();
			PreparedStatement ps;
			ResultSet rs;
			ps=conn.prepareStatement(sql);
			ps.setString(1, openid);
			rs=ps.executeQuery();
			if(rs.next()){
				chatCategory =rs.getInt("chat_category");
				
			}
			JDBCUtils_c3p0.release(conn, ps, rs);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return chatCategory;
	}

	@Override
	public String getSubknowledge(int knoledgeId) {
	  String knowledgeAnswer="";
	  String sql="select answer from knowledge_sub where pid=? order by rand() limit 0,1";
	  Connection conn=null;
	  PreparedStatement ps=null;
		ResultSet rs=null;
	  try {
		 conn=ConnectToDatabase.GetConnection();
		
		ps=conn.prepareStatement(sql);
		ps.setInt(1,knoledgeId);
		rs=ps.executeQuery();
		if(rs.next()){
			knowledgeAnswer=rs.getString("answer");
			
		}
		
	} catch (ClassNotFoundException e) {
	
		e.printStackTrace();
	} catch (SQLException e) {
	
		e.printStackTrace();
	}finally{
		JDBCUtils_c3p0.release(conn, ps, rs);
	}	
		return knowledgeAnswer;
	}

	@Override
	public String getJoke() {
		String jokecontent="";
		String sql="select joke_content from joke order by rand() limit 0,1";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		 try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				jokecontent=rs.getString("joke_content");
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			JDBCUtils_c3p0.release(conn, ps, rs);
		}
		return jokecontent;
	}

	@Override
	public void saveChatLog(String openid, String createTime, String reqMeg,
			String respMeg, int chatCategry) {
		String sql="insert into chat_log(open_id,create_time,req_msg,resp_msg,chat_category) values(?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, openid);
			ps.setString(2, createTime);
			ps.setString(3, reqMeg);
			ps.setString(4, respMeg);
			ps.setInt(5, chatCategry);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally{
			JDBCUtils_c3p0.release(conn, ps, rs);
		}
	}

	//添加客服消息
	public void saveChatInfo(String question, String answer) {
		
		String sql="insert into knowledge (question,answer,category) values(?,?,?)";
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=ConnectToDatabase.GetConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, question);
			ps.setString(2, answer);
			ps.setInt(3, 1);
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			log.info("客服消息添加失败"+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			log.info("客服消息添加失败"+e.getMessage());
			e.printStackTrace();
		}finally{
			JDBCUtils_c3p0.release(conn, ps, rs);
		}
	}

}
