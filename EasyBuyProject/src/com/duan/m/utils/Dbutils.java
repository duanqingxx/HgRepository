package com.duan.m.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Dbutils {
	private static Properties properties=null;
	
	static{  
		properties=new Properties();
		try {
			properties.load(Dbutils.class.getResourceAsStream("/init.properties"));
		} catch (IOException e) {
			System.out.println("Properties Error");
		}
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		StringBuffer url=new StringBuffer();
		String ip=properties.getProperty("ip").trim().toLowerCase();
		String port=properties.getProperty("port").trim().toLowerCase();
		String dbtype=properties.getProperty("dbtype").trim().toLowerCase();
		String dbname=properties.getProperty("dbname").trim().toLowerCase();
		String username=properties.getProperty("username").trim().toLowerCase();
		String password=properties.getProperty("password").trim().toLowerCase();
		if(dbtype.equals("oracle")){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				System.out.println("Driver Error");
			}
			url.append("jdbc:oracle:thin:@");
			url.append(ip);
			url.append(":");
			url.append(port);
			url.append(":");
			url.append(dbname);
			
		}
		try {
			conn=DriverManager.getConnection(url.toString(), username, password);
//			System.out.println(conn.toString());
		} catch (SQLException e) {
			System.out.println("Connection Error");
		}
		return conn;
	}
	
	
	public static void close(Connection conn,PreparedStatement ptm,
			ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Connection Close Error");
			}
		}
		if(ptm!=null){
			try {
				ptm.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement Close Error");
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet Close Error");
			}
		}
	}
	
	
}
