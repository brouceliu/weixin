package org.zr.model.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.CardTicket;
import org.zr.entity.card;
import org.zr.util.JDBCUtils_c3p0;




public class CardNumDaoImol implements CardNumDao{

	//static Connection conn;
	private static Logger log = LoggerFactory.getLogger(CardTicketDaoImpl.class);

	@Override
	public void addCard(card ca) {
		try {
			Connection	conn=ConnectToDatabase.GetConnection();
			log.info("����cardnum�����ݿ�");
			String cardtype=ca.getCardtype();
			String cardstatus=ca.getCardstatus();
			String cardcode=ca.getCardcode();
			String cardpasswd=ca.getCardpasswd();
			String cardid=ca.getCardid();
			String sql="insert into cardnum (cordtype,cardcode,cardstatus,cardpassword,cardid) values (?,?,?,?,?)";
			PreparedStatement ps;
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, cardtype);
				ps.setString(2, cardcode);
				ps.setString(3, cardstatus);
				ps.setString(4,cardpasswd);
				ps.setString(5,cardid);
				int rowcount = ps.executeUpdate();
				log.info(rowcount + "���뿨�������Ϣ�ɹ�");
				 JDBCUtils_c3p0.release(conn, ps, null);
			//	ps.close();
				//conn.close(); // �ر����ݿ�����
			} catch (Exception e) {
				StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();   
				log.info("�������ݿ����"+sw.toString());
				e.printStackTrace();
			}
		
	}

	@Override
	public void updateCard(card ca) {
		String canum=ca.getCardcode()+"-p-";
		String passwd=ca.getCardpasswd();
		log.info("cardnumber is "+canum);
		try {
			Connection conn=	ConnectToDatabase.GetConnection();
			log.info("����card");			
			String sql="update cardnum set cardstatus = ? where cardcode = ? and cardpassword=?";
			PreparedStatement ps;
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, "yes");
				ps.setString(2, canum);
				ps.setString(3, passwd);
				int rowcount = ps.executeUpdate();
				log.info(rowcount + "���¿���ɹ�");
				JDBCUtils_c3p0.release(conn, ps, null);
			//	ps.close();
			//	conn.close(); // �ر����ݿ�����
			} catch (Exception e) {
				StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();   
				log.info("���¿������"+sw.toString());
				e.printStackTrace();
			}
		
		
	}

	@Override
	public card getCardfrom(String cardid) {
		String sql = "select * from cardnum where cardstatus= ? and cardid= ? order by rand() limit 1";
		card ca=new card();
		try {
		log.info("����cardid��ѯ δʹ�õ�card");
		Connection conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per =  conn.prepareStatement(sql);
	        per.setString(1, "no");
	        per.setString(2, cardid);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
				
			ca.setCardtype(rs.getString("cordtype"));
			ca.setCardcode(rs.getString("cardcode"));
			ca.setCardstatus(rs.getString("cardstatus"));
			ca.setCardpasswd(rs.getString("cardpassword"));
			ca.setCardid(rs.getString("cardid"));
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
			log.info("��ѯ����"+sw.toString());
			e.printStackTrace();
		}
		log.info("ȡ��ca"+ca.getCardcode()+"ȡ������"+ca.getCardtype());
		return ca;
	}
	@Override
	public card getCardid(String id) {
		/*String sql = "select * from cardnum where cardid=? ";
		card ca=new card();
		try {
		log.info("����id��ѯcard");
		Connection conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
	       per.setString(1, id);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
			ca.setCardtype(rs.getString("cordtype"));
			ca.setCardcode(rs.getString("cardcode"));
			ca.setCardstatus(rs.getString("cardstatus"));
			ca.setCardpasswd(rs.getString("cardpassword"));
			ca.setCardid(rs.getString("cardid"));
			}
			JDBCUtils_c3p0.release(conn, per, rs);
         //   rs.close();
		//	per.close();
		//	conn.close();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("�������ݿ����"+sw.toString());
			log.info("��ѯ����"+sw.toString());
			e.printStackTrace();
		}
		//log.info("ȡ��code>>>>>"+ca.getCardcode()+"ȡ������>>>>>"+ca.getCardtype());*/
		return null;
	}
	
	public card getCardBypasswd(String code, String passwd) {
		// ͨ������ code passwd ȡ�ÿ���� type card
		String sql = "select * from cardnum where cardcode=? and cardpassword= ? ";
		card ca=new card();
		try {
		log.info("���տ������ѯcard");
		Connection conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
	       per.setString(1, code);                                                                                                                                                                                              
	       per.setString(2, passwd);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
			ca.setCardtype(rs.getString("cordtype"));
			ca.setCardcode(rs.getString("cardcode"));
			ca.setCardstatus(rs.getString("cardstatus"));
			ca.setCardpasswd(rs.getString("cardpassword"));
			}
			JDBCUtils_c3p0.release(conn, per, rs);
          //  rs.close();
		//	per.close();
			//conn.close();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("�������ݿ����"+sw.toString());
			log.info("��ѯ����"+sw.toString());
			e.printStackTrace();
		}
		log.info("ȡ��code>>>>>"+ca.getCardcode()+"ȡ������>>>>>"+ca.getCardtype());
		return ca;
		
		
		
	}
	
	public card getCardByCode(String code) {
		// ͨ������ code passwd ȡ�ÿ���� type card
				String sql = "select * from cardnum where cardcode=?  ";
				card ca=new card();
				try {
				log.info("����code��ѯcard");
				Connection conn=ConnectToDatabase.GetConnection();
			        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			       per.setString(1, code);                                                                                                                                                                                              
			     
					ResultSet rs = per.executeQuery();
					while(rs.next()){
					ca.setCardtype(rs.getString("cordtype"));
					ca.setCardcode(rs.getString("cardcode"));
					ca.setCardstatus(rs.getString("cardstatus"));
					ca.setCardpasswd(rs.getString("cardpassword"));
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
					log.info("��ѯ����"+sw.toString());
					e.printStackTrace();
				}
				log.info("ȡ��code>>>>>"+ca.getCardcode()+"ȡ������>>>>>"+ca.getCardtype());
				return ca;
	}
		
	

}
