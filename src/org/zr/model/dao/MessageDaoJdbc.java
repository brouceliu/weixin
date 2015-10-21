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
	// ������̬ȫ�ֱ���
	//	static Connection conn;
	/**����jdbc ʵ��**/
	
	
	
	
	/**����
	 * @throws SQLException 
	 * @throws ClassNotFoundException **/
	public String insertMessage(BaseMessage base) {
		try {
			String a="/n";
			Connection	 conn=ConnectToDatabase.GetConnection();
		log.info("������Ϣ�û��������Ϣ");
		long time = base.getCreateTime();
		String duserid = base.getFromUserName();// ���շ�
		String userid = base.getToUserName();// ���ͷ�
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
			log.info(rowcount + "������Ϣ�ɹ�");
			//ps.close();
			//conn.close(); // �ر����ݿ�����
			JDBCUtils_c3p0.release(conn, per, null);
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
           
            log.warn("�������ݿ����"+sw.toString());
			e.printStackTrace();
		}
		
		
		return "success";
	}

	
	public List<BaseMessage> selectByMid(String mid) {
		String sql = "select * from message where msgid = ?";
		List<BaseMessage> tolist = new ArrayList<BaseMessage>();
		try {
		log.info("��ѯ�Ƿ���id��ͬ����Ϣ");
		Connection	 conn=ConnectToDatabase.GetConnection();
			PreparedStatement per = conn.prepareStatement(sql);
			per.setString(1, mid);
			ResultSet rs = per.executeQuery();
			while (rs.next()) { // �ж��Ƿ�����һ������
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
			//log.info("�������ݿ����"+sw.toString());
            log.warn("��ѯ����"+sw.toString());
			e.printStackTrace();
		}
		
		return tolist;
	}

	
	public String deleteMessage(String time) {
		
		return null;
	} 
	//��΢�Ŵ����ʱ��ת��Ϊ ��׼ʱ���ʽ
	public static String formatTime(String longTime){
		//��ǰʱ��  longtiem ָ���� ����1970��1��1�� 0miu 0sec 0����� ������
		long messageTime=Long.parseLong(longTime)*1000L;//ת��Ϊ����
		DateFormat format=new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
		return format.format(new Date(messageTime));
	}
	
	
	/**���� text��Ϣ�����ݿ�
	 * @throws SQLException 
	 * @throws ClassNotFoundException **/
	public String insertTextMessage(TextMessage text,String username) {
		try {
			Connection		 conn=ConnectToDatabase.GetConnection();
		log.info("��ʼ����");
		long time = text.getCreateTime();//����ʱ��
		String duserid = text.getFromUserName();// ���շ�
		String userid = text.getToUserName();// ���ͷ�
	    String content= text.getContent();//��Ϣ����
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
			log.info(rowcount + "�����û�����Ϣ�ɹ�");
			JDBCUtils_c3p0.release(conn, per, null);
			//ps.close();
		//	conn.close(); // �ر����ݿ�����
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();     
			log.warn("�������ݿ����"+sw.toString());
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public  List<TextMessage> selectText() {
		List<TextMessage> list=new ArrayList<TextMessage>();
		try {
			Connection	 conn=ConnectToDatabase.GetConnection();
		log.info("��ѯһ����Ϣ");
		String sql="select * from textmeg";
		
				PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = per.executeQuery();
				while (rs.next()) { // �ж��Ƿ�����һ������
					TextMessage test=new TextMessage();
					test.setContent(rs.getString("content"));
					test.setCreateTime(rs.getLong("time"));
					test.setFromUserName(rs.getString("duserid"));
					test.setToUserName(rs.getString("userid"));//�û�id
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
				//log.info("�������ݿ����"+sw.toString());
	            log.warn("��ѯ��Ϣ����"+sw.toString());
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
		// ����ʱ���ѯ��Ϣ
		String sql = "select * from textmeg where time>=? and time<=?";
		List<TextMessage> list=new ArrayList<TextMessage>();
		try {
		log.info("��ѯʱ��ڵ���Ϣ");
		Connection conn=ConnectToDatabase.GetConnection();
			PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			per.setString(1, timeStart);
			per.setString(2, timeEnd);
			ResultSet rs = per.executeQuery();
			while (rs.next()) { // �ж��Ƿ�����һ������
				TextMessage test=new TextMessage();
				test.setContent(rs.getString("content"));
				test.setCreateTime(rs.getLong("time"));
				test.setFromUserName(rs.getString("duserid"));
				test.setToUserName(rs.getString("userid"));//�û�id
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
			//log.info("�������ݿ����"+sw.toString());
            log.warn("��ѯ����"+sw.toString());
			e.printStackTrace();
		}
		
		return list;
		
	
	}
}
