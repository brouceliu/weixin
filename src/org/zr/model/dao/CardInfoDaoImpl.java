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
import org.zr.entity.CardInfo;
import org.zr.entity.card;
import org.zr.util.JDBCUtils_c3p0;




public class CardInfoDaoImpl implements CardInfoDao{
	//static Connection conn;
	private static Logger log = LoggerFactory.getLogger(CardInfoDaoImpl.class);

	@Override
	public void addCardInfo(CardInfo ci) {
		try {
			Connection	conn=ConnectToDatabase.GetConnection();
			log.info("插入cardinfo到数据库");
			String cardtype=ci.getCardtype();
			String cardtime=ci.getCardtime();
			String cardsku=ci.getCardsku();
			String id=ci.getCardid();
			
			String sql="insert into cardinfo (cordtype,cardsku,cardid,cardtime) values (?,?,?,?)";
			PreparedStatement ps;
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, cardtype);
				ps.setString(2, cardsku);
				ps.setString(3, id);
				ps.setString(4,cardtime);
				int rowcount = ps.executeUpdate();
				log.info(rowcount + "插入信息成功");
				/*ps.close();
				conn.close(); // 关闭数据库连接*/	
				 JDBCUtils_c3p0.release(conn, ps, null);
				
				} catch (Exception e) {
				StringWriter sw = new StringWriter();   
	            PrintWriter pw = new PrintWriter(sw, true);   
	            e.printStackTrace(pw);   
	            pw.flush();   
	            sw.flush();   
				log.info("插入数据库错误"+sw.toString());
				e.printStackTrace();
			}
		
	}

	@Override
	public CardInfo getCardInfo(String type) {
		String sql = "select * from cardinfo where cordtype=?";
		CardInfo ca=new CardInfo();
		try {
		log.info("查询 cardinfo");
		Connection	conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
	        per.setString(1, type);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
			ca.setCardid(rs.getString("cardid"));
			ca.setCardsku(rs.getString("cardsku"));
			ca.setCardtype(rs.getString("cordtype"));
			ca.setCardtime(rs.getString("cardtime"));
			}
			JDBCUtils_c3p0.release(conn, per, rs);
          /*  rs.close();
			per.close();
			conn.close();*/
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("插入数据库错误"+sw.toString());
			log.info("查询错误"+sw.toString());
			e.printStackTrace();
		}
		
		return ca;
		
	}

	@Override
	public CardInfo getCardInfoById(String id) {
		String sql = "select * from cardinfo where id=?";
		CardInfo ca=new CardInfo();
		try {
		log.info("查询 cardinfo");
		Connection	conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
	        per.setString(1, id);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
			ca.setCardid(rs.getString("cardid"));
			ca.setCardsku(rs.getString("cardsku"));
			ca.setCardtype(rs.getString("cordtype"));
			ca.setCardtime(rs.getString("cardtime"));
			}
			JDBCUtils_c3p0.release(conn, per, rs);
          /*  rs.close();
			per.close();
			conn.close();*/
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("插入数据库错误"+sw.toString());
			log.info("查询错误"+sw.toString());
			e.printStackTrace();
		}
		
		return ca;
		
	}

}
