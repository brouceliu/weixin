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
import org.zr.entity.JsTicket;
import org.zr.util.JDBCUtils_c3p0;




public class CardTicketDaoImpl implements CardTicketDao{

	//static Connection conn;
	private static Logger log = LoggerFactory.getLogger(CardTicketDaoImpl.class);
/**存储accesstoken **/
	
	public  void addCardTicket(CardTicket card) {
				try {
					Connection	conn=ConnectToDatabase.GetConnection();
					log.info("插入cardticket到数据库");
					String ticket=card.getTicket();
					String exptime=String.valueOf(card.getExptime());//token y有效秒数
					String endtime=card.getEndtime();
					String sql="insert into cardticket (ticket,exptime,endtime) values (?,?,?)";
					PreparedStatement per;
						per = (PreparedStatement) conn.prepareStatement(sql);
						per.setString(1, ticket);
						per.setString(2, exptime);
						per.setString(3, endtime);
						int rowcount = per.executeUpdate();
						log.info(rowcount + "插入信息成功");
						JDBCUtils_c3p0.release(conn, per, null);
					//	ps.close();
					//	conn.close(); // 关闭数据库连接
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

	
	public CardTicket findTicket() {
		
		String sql = "select * from cardticket";
		CardTicket ticket=new CardTicket();
		try {
		log.info("查询 ticket");
		Connection	conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
				ticket.setExptime(Integer.parseInt(rs.getString("exptime")));
				ticket.setTicket(rs.getString("ticket"));
				ticket.setEndtime(rs.getString("endtime"));
			}
			JDBCUtils_c3p0.release(conn, per, rs);
           // rs.close();
		//	per.close();
			//conn.close();
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
		log.info("取得ticket时间"+ticket.getEndtime()+"有效时间"+ticket.getExptime());
		return ticket;
	}

}
