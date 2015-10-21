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
import org.zr.util.JDBCUtils_c3p0;
import org.zr.util.TimeUtil;



public class FileNameDaoImpl implements FileNameDao{

	//static Connection conn;
	private static Logger log = LoggerFactory.getLogger(FileNameDaoImpl.class);


	public void addFileName(String filename) {
		
		try {
			Connection	conn=ConnectToDatabase.GetConnection();
			log.info("����cardticket�����ݿ�");
			
			String sql="insert into filename (filename,time) values (?,?)";
			PreparedStatement ps;
				ps = (PreparedStatement) conn.prepareStatement(sql);
				String time=TimeUtil.getNowTime();
				ps.setString(1, filename);
				ps.setString(2, time);
			
				int rowcount = ps.executeUpdate();
				log.info(rowcount + "������Ϣ�ɹ�");
				 JDBCUtils_c3p0.release(conn, ps, null);
				//ps.close();
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

	
	public String getFileName() {
		String sql = "select * from filename";
		String filename="";
		try {
		log.info("��ѯ filename");
		Connection	conn=ConnectToDatabase.GetConnection();
	        PreparedStatement per = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = per.executeQuery();
			while(rs.next()){
				filename=rs.getString("filename");
			}
			JDBCUtils_c3p0.release(conn, per, rs);
          //  rs.close();
			//per.close();
			//conn.close();
		} catch (Exception e) {
			StringWriter sw = new StringWriter();   
            PrintWriter pw = new PrintWriter(sw, true);   
            e.printStackTrace(pw);   
            pw.flush();   
            sw.flush();   
			//log.info("�������ݿ����"+sw.toString());
			log.error("��ѯ����"+sw.toString());
			e.printStackTrace();
		}
		
		return filename;
	
	}

}
