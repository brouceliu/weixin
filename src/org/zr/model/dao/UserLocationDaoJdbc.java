package org.zr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zr.entity.UserLocation;





import org.zr.util.JDBCUtils_c3p0;

import com.opensymphony.xwork2.Result;

public class UserLocationDaoJdbc implements UserLocationDao {
	private static Logger log = LoggerFactory.getLogger(UserLocationDaoJdbc.class);
	// ������̬ȫ�ֱ���
		static Connection conn;

	
	
	
	@Override
	public void saveLocation(UserLocation location) {
		
        try {
        	log.info("�洢�û�������Ϣ");
        	String sql="insert into user_location (openid,lng,lat,bd_lng,bd_lat) values (?,?,?,?,?)";
        	 conn=ConnectToDatabase.GetConnection();
			String openid=location.getOpenId();
			String lat=location.getLat();
			String lng=location.getLng();
			String bd_lng=location.getBd09Lng();
			String bd_lat=location.getBd09Lat();
			log.info("user id is "+openid);
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, openid);
			ps.setString(2, lat);
			ps.setString(3, lng);
			ps.setString(4, bd_lng);
			ps.setString(5, bd_lat);
			ps.executeUpdate();
			//ps.close();
		//	conn.close();
			 JDBCUtils_c3p0.release(conn, ps, null);
			log.info("�����洢");
		} catch (Exception e) {
			log.warn("�û���Ϣ�洢����"+e.toString());
			e.printStackTrace();
		}finally{
			//ps.close();
		
		}
	}

	@Override
	public UserLocation getLastlocation(String openid) {
		log.info("select  id is "+openid);
		UserLocation userlocation=null;
		String sql="select * from user_location where openid = ?";
		try {
			 conn=ConnectToDatabase.GetConnection();
			PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, openid);
			log.info("��ʼ��ѯ�û�λ��"+openid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				log.info("id is "+rs.getInt("id"));
				userlocation=new UserLocation();
				userlocation.setOpenId(rs.getString("openid"));
				userlocation.setLng(rs.getString("lng"));
				userlocation.setLat(rs.getString("lat"));
				userlocation.setBd09Lng(rs.getString("bd_lng"));
				userlocation.setBd09Lat(rs.getString("bd_lat"));
			}
			JDBCUtils_c3p0.release(conn, ps, rs);
			//rs.close();
		//	ps.close();
			//conn.close();
		}catch (Exception e) {
			log.warn("��ѯ�û�����λ����Ϣʧ��");
			e.printStackTrace();
		}
		
		return userlocation;
	}

}
