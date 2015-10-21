package org.zr.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.zr.util.JDBCUtils_c3p0;

public class ConnectToDatabase {
	
	public static Connection GetConnection() throws ClassNotFoundException, SQLException {
		Connection con=null;
		//加载驱动
		
			//Class.forName("com.mysql.jdbc.Driver");
			/*con = DriverManager
					.getConnection(
						"jdbc:mysql://10.0.16.16:4066/71048194m_mysql_nkhqis5n?useUnicode=true&amp;characterEncoding=UTF-8",
						"R3O1qUwo", "v4u3E41AnbXF");// 创建数据连接
	"jdbc:mysql://localhost:3306/lzr?useUnicode=true&amp;characterEncoding=UTF-8","root", "123456");// 创建数据连接
	
*/	
			con = JDBCUtils_c3p0.getConnection();
			return con;
	}
	
	
	
}
