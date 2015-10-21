package org.zr.model.dao;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.message.zr.request.BaseMessage;
import org.message.zr.request.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.service.CoreService;
import org.zr.util.JDBCUtils_c3p0;






public class MessageDaoJdbc implements MessageDao{
	private static Logger log = LoggerFactory.getLogger(MessageDaoJdbc.class);
	// 创建静态全局变量
	//	static Connection conn;
	/**改用jdbc 实现**/
	
	
	
	
	/**插入
	 * @throws SQLException 
	 * @throws ClassNotFoundException **/
	public String insertMessage(BaseMessage base) {
		try {
			String a="/n";
			Connection	 conn=ConnectToDatabase.GetConnection();
		log.info("插入消息用户输入的消息");
		long time = base.getCreateTime();
		String duserid = base.getFromUserName();// 接收方
		String userid = base.getToUserName();// 发送方
		long msgid = base.getMsgId();
		String msgtype = base.getMsgType();
		String sql="insert into message (msgid,time,duserid,userid,mtype) values (?,?,?,?,?)";
		PreparedStatement per;
			per = (PreparedStatement) conn.prepareStatement(sql);
			per.setLong(1, msgid);
			per.setLong(2, time);
			per.setString(3, duserid);
			per.setString(4, userid);
			per.setString(5, msgtype);
			int rowcount = per.executeUpdate();
			log.info(rowcount + "插入消息成功");
			//ps.close();
			//conn.close(); // 关闭数据库连接
			JDBCUtils_c3p0.release(conn, per, null);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
           
            log.warn("插入数据库错误"+sw.toString());
			e.printStackTrace();
		}
		
		
		return "success";
	}

	
	public List<BaseMessage> selectByMid(String mid) {
		String sql = "select * from message where msgid = ?";
		List<BaseMessage> tolist = new ArrayList<BaseMessage>();
		try {
		log.info("查询是否有id相同的消息");
		Connection	 conn=ConnectToDatabase.GetConnection();
			PreparedStatement per = conn.prepareStatement(sql);
			per.setString(1, mid);
			ResultSet rs = per.executeQuery();
			while (rs.next()) { // 判断是否还有下一个数据
				BaseMessage bs=new BaseMessage();
				bs.setCreateTime(rs.getLong("time"));
				bs.setMsgId(rs.getLong("msgid"));
				bs.setFromUserName(rs.getString("duserid"));
				bs.setToUserName(rs.getString("userid"));
				bs.setMsgType(rs.getString("mtype"));
				tolist.add(bs);
			}
			JDBCUtils_c3p0.release(conn, per, rs);
          //  rs.close();
		//	per.close();
		//	conn.close();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("插入数据库错误"+sw.toString());
            log.warn("查询错误"+sw.toString());
			e.printStackTrace();
		}
		
		return tolist;
	}

	
	public String deleteMessage(String time) {
		
		return null;
	} 
	//将微信传入的时间转化为 标准时间格式
	public static String formatTime(String longTime){
		//当前时间  longtiem 指的是 距离1970年1月1日 0miu 0sec 0毫秒的 毫秒数
		long messageTime=Long.parseLong(longTime)*1000L;//转化为秒数
		DateFormat format=new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
		return format.format(new Date(messageTime));
	}
	
	
	/**插入 text消息到数据库
	 * @throws SQLException 
	 * @throws ClassNotFoundException **/
	public String insertTextMessage(TextMessage text,String username) {
		try {
			Connection		 conn=ConnectToDatabase.GetConnection();
		log.info("开始插入");
		long time = text.getCreateTime();//创建时间
		String duserid = text.getFromUserName();// 接收方
		String userid = text.getToUserName();// 发送方
	    String content= text.getContent();//消息内容
		String msgtype = text.getMsgType();
		String sql="insert into textmeg (time,duserid,userid,mtype,content,nickname) values (?,?,?,?,?,?)";
		PreparedStatement per;
			per = (PreparedStatement) conn.prepareStatement(sql);
			per.setLong(1, time);
			per.setString(2, duserid);
			per.setString(3, userid);
			per.setString(4, msgtype);
			per.setString(5, content);
			per.setString(6, username);
			int rowcount = per.executeUpdate();
			log.info(rowcount + "插入用户的消息成功");
			JDBCUtils_c3p0.release(conn, per, null);
			//ps.close();
		//	conn.close(); // 关闭数据库连接
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();     
			log.warn("插入数据库错误"+sw.toString());
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public  List<TextMessage> selectText() {
		List<TextMessage> list=new ArrayList<TextMessage>();
		try {
			Connection	 conn=ConnectToDatabase.GetConnection();
		log.info("查询一切消息");
		String sql="select * from textmeg";
		
				PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = per.executeQuery();
				while (rs.next()) { // 判断是否还有下一个数据
					TextMessage test=new TextMessage();
					test.setContent(rs.getString("content"));
					test.setCreateTime(rs.getLong("time"));
					test.setFromUserName(rs.getString("duserid"));
					test.setToUserName(rs.getString("userid"));//用户id
					test.setUsername(rs.getString("nickname"));
					list.add(test);
				}
				JDBCUtils_c3p0.release(conn, per, rs);
	           // rs.close();
				//per.close();
				//conn.close();
			} catch (Exception e) {
				StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();   
				//log.info("插入数据库错误"+sw.toString());
	            log.warn("查询消息错误"+sw.toString());
				e.printStackTrace();
			}
		
		
		return list;
	}
/*public static void main(String[] args) {
	MessageDao mdao=new MessageDaoJdbc();

	System.out.println(mdao.selectText());
}*/



	@Override
	public List<TextMessage> selectTextByTime(String timeStart, String timeEnd) {
		// 按照时间查询消息
		String sql = "select * from textmeg where time>=? and time<=?";
		List<TextMessage> list=new ArrayList<TextMessage>();
		try {
		log.info("查询时间节点消息");
		Connection conn=ConnectToDatabase.GetConnection();
			PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			per.setString(1, timeStart);
			per.setString(2, timeEnd);
			ResultSet rs = per.executeQuery();
			while (rs.next()) { // 判断是否还有下一个数据
				TextMessage test=new TextMessage();
				test.setContent(rs.getString("content"));
				test.setCreateTime(rs.getLong("time"));
				test.setFromUserName(rs.getString("duserid"));
				test.setToUserName(rs.getString("userid"));//用户id
				test.setUsername(rs.getString("nickname"));
				list.add(test);
			}
			JDBCUtils_c3p0.release(conn, per, rs);
          //  rs.close();
		//	per.close();
		//	conn.close();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("插入数据库错误"+sw.toString());
            log.warn("查询错误"+sw.toString());
			e.printStackTrace();
		}
		
		return list;
		
	
	}
}
