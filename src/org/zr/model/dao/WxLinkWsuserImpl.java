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
        /**���󶨵����ݴ������ݿ�***/
	private static Logger log = LoggerFactory.getLogger(WxLinkWsuserImpl.class);
	// ������̬ȫ�ֱ���
		static Connection conn;

	
	
	
	/**�洢**/
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
			log.info("ERROR ��ʧ��"+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			log.info("ERROR ��ʧ��"+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**����openid ȡ��**/
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
			log.info("ERROR ����openidʧ��");
			e.printStackTrace();
		} catch (SQLException e) {
			log.info("ERROR ����openidʧ��");
			e.printStackTrace();
		}
		
		return ls;
	}
	
}
