package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class BaseConnection {
	private static String conURL = "";
	private static String cname = "";
	
	private static String dbA = "";
	private static String dbpassword = "";
	
	static
	{
		try {
			//获取外部配置的数据库链接信息
			InputStream ips =BaseConnection.class.getClassLoader().getResourceAsStream("MyJDBC.properties");
			Properties props = new Properties();
			try {
				props.load(ips);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ips.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conURL = props.getProperty("conURL");
			cname =	props.getProperty("cname");
			dbA = props.getProperty("dbA");
			dbpassword = props.getProperty("dbpassword");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//
	public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(cname);
            conn = DriverManager.getConnection(conURL, dbA, dbpassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
	public static void closeRes(Connection conn, PreparedStatement ps){
		try {
			if ( conn != null ) {
				conn.close();
			}
		
			if ( ps != null ) {
				ps.close();
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	public static void closeRes(Connection conn, PreparedStatement ps,ResultSet rs){
		try {
			if ( conn != null ) {
				conn.close();
			}
		
			if ( ps != null ) {
				ps.close();
			}
			
			if ( rs != null ) {
				rs.close();
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

}



