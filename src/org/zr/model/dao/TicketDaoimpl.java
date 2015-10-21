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
import org.zr.entity.AccessToken;
import org.zr.entity.JsTicket;
import org.zr.util.JDBCUtils_c3p0;




public class TicketDaoimpl implements TicketDao{

	//static Connection conn;
	private static Logger log = LoggerFactory.getLogger(TicketDaoimpl.class);

	
	
	
	
	public void addTicket(JsTicket tick) {
		
		try {
			Connection	 conn=ConnectToDatabase.GetConnection();
			log.info("插入ticket到数据库");
			String ticket=tick.getJskey();
			String exptime=String.valueOf(tick.getExpiresIn());//token y有效秒数
			String gettime=tick.getEndTime();
			String sql="insert into jsticket (ticket,exptime,gettime) values (?,?,?)";
			PreparedStatement ps;
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, ticket);
				ps.setString(2, exptime);
				ps.setString(3, gettime);
				int rowcount = ps.executeUpdate();
				log.info(rowcount + "插入信息成功");
				 JDBCUtils_c3p0.release(conn, ps, null);
				//ps.close();
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

	@Override
	public JsTicket getTicket() {
		String sql = "select * from jsticket WHERE Id=(SELECT MAX(id) FROM jsticket)";
		//select * from jsticket WHERE Id=(SELECT MAX(id) FROM jsticket);
		JsTicket ticket=new JsTicket();
		try {
		log.info("查询 ticket");
		Connection conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
				ticket.setExpiresIn(Integer.parseInt(rs.getString("exptime")));
				ticket.setJskey(rs.getString("ticket"));
				ticket.setEndTime(rs.getString("gettime"));
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
			log.info("查询错误"+sw.toString());
			e.printStackTrace();
		}
		log.info("取得ticket时间"+ticket.getEndTime()+"有效时间"+ticket.getExpiresIn());
		return ticket;
	}

}
