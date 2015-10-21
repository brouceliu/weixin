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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.AccessToken;
import org.zr.util.JDBCUtils_c3p0;
import org.zr.util.TimeUtil;



public class AccessTokenImpl {
	// 创建静态全局变量
			//static Connection conn;
	private static Logger log = LoggerFactory.getLogger(AccessTokenImpl.class);

	
	
	
	/**向数据库插入token**/
	
	public static String insertoken(AccessToken token) {
		try {
			Connection conn=ConnectToDatabase.GetConnection();
		log.info("插入token到数据库");
		String accessto=token.getToken();
		String exptime=String.valueOf(token.getExpiresIn());//token y有效秒数
		String gettime=token.getGettime();
		String sql="insert into token (token,exptime,gettime) values (?,?,?)";
		PreparedStatement ps;
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, accessto);
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
		return "success";
	}
	
	public static AccessToken selectToken() {
		
		String sql = "select * from token";
		AccessToken to=new AccessToken();
		try {
		log.info("查询 token");
		Connection conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
				to.setExpiresIn(Integer.parseInt(rs.getString("exptime")));
				to.setToken(rs.getString("token"));
				to.setGettime(rs.getString("gettime"));
			}
         //  rs.close();
			//per.close();
		//	conn.close();
			JDBCUtils_c3p0.release(conn, per, rs);
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
		log.info("取得token时间"+to.getGettime()+"有效时间"+to.getExpiresIn());
		return to;
	}

	
	

}
