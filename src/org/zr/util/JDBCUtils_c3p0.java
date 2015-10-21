package org.zr.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


 /*@ClassName: JdbcUtils_C3P0
* @Description: ���ݿ����ӹ�����
* @author: �°�����
* @date: 2014-10-4 ����6:04:36*/


public class JDBCUtils_c3p0 {
    
    private static ComboPooledDataSource ds = null;
    //�ھ�̬������д������ݿ����ӳ�
    static{
        try{
            //ͨ�����봴��C3P0���ݿ����ӳ�
        	  ds = new ComboPooledDataSource("MySQL");
        //    ds = new ComboPooledDataSource();
          /*  ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/lzr");
            ds.setUser("root");
            ds.setPassword("123456");
            ds.setInitialPoolSize(10);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(20);*/
            
            //ͨ����ȡC3P0��xml�����ļ���������Դ��C3P0��xml�����ļ�c3p0-config.xml�������srcĿ¼��
            //ds = new ComboPooledDataSource();//ʹ��C3P0��Ĭ����������������Դ
          //ʹ��C3P0��������������������Դ
            
        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
   
   /* * @Method: getConnection
    * @Description: ������Դ�л�ȡ���ݿ�����
    * @Anthor:�°�����
    * @return Connection
    * @throws SQLException
    **/
    public static Connection getConnection() throws SQLException{
        //������Դ�л�ȡ���ݿ�����
        return ds.getConnection();
    }
    
    /**
    * @Method: release
    * @Description: �ͷ���Դ��
    * �ͷŵ���Դ����Connection���ݿ����Ӷ��󣬸���ִ��SQL�����Statement���󣬴洢��ѯ�����ResultSet����
    * @Anthor:�°�����
    *
    * @param conn
    * @param st
    * @param rs
    */
    public static void release(Connection conn,PreparedStatement st,ResultSet rs){
        if(rs!=null){
            try{
                //�رմ洢��ѯ�����ResultSet����
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //�رո���ִ��SQL�����Statement����
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(conn!=null){
            try{
                //��Connection���Ӷ��󻹸����ݿ����ӳ�
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}