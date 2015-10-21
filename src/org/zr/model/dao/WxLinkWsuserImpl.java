package org.zr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.WxLinkWs;
import org.zr.util.JDBCUtils_c3p0;







public class WxLinkWsuserImpl {
        /**将绑定的数据存入数据库***/
	private static Logger log = LoggerFactory.getLogger(WxLinkWsuserImpl.class);
	// 创建静态全局变量
		static Connection conn;

	
	
	
	/**存储**/
	public static boolean save(String openid,String wsuser,String wspasswd){
		boolean result=false;
		try {
			 conn=ConnectToDatabase.GetConnection();
    	String sql="insert into wxwushang (time,openid,wsuser,wspasswd) values(?,?,?,?)";
    	Date date=new Date();
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String time=df.format(date);
    	PreparedStatement ps= conn.prepareStatement(sql);
    	ps.setString(1, time);
    	ps.setString(2, openid);
    	ps.setString(3, wsuser);
    	ps.setString(4, wspasswd);
        ps.executeUpdate();
        result=true;
        JDBCUtils_c3p0.release(conn, ps, null);
       // ps.close();
     //   conn.close();
    //	return result;
		} catch (ClassNotFoundException e) {
			log.info("ERROR 绑定失败"+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			log.info("ERROR 绑定失败"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**根据openid 取出**/
	public static WxLinkWs findByOpenId(String openid){
		WxLinkWs ls=null;
		String sql="select * from wxwushang where openid=?";
		try {
			 conn=ConnectToDatabase.GetConnection();
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1, openid);
			ResultSet set=ps.executeQuery();
			while(set.next()){
				ls=new WxLinkWs();
				ls.setOpenid(openid);
				ls.setTime(set.getString("time"));
				ls.setWspasswd(set.getString("wspasswd"));
				ls.setWsuser(set.getString("wsuser"));
			}
			JDBCUtils_c3p0.release(conn, ps, set);
			//set.close();
		//	ps.close();
		//	conn.close();
		} catch (ClassNotFoundException e) {
			log.info("ERROR 查找openid失败");
			e.printStackTrace();
		} catch (SQLException e) {
			log.info("ERROR 查找openid失败");
			e.printStackTrace();
		}
		
		return ls;
	}
	
}
