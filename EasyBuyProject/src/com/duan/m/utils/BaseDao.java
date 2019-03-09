package com.duan.m.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDao {
	public Connection getConnection () {
		Context ctx;
		Connection conn=null;
		try {
			ctx = new InitialContext();
			//获取与逻辑名相关联的数据源对象
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/news");
			conn=ds.getConnection();	             
		} catch (SQLException exception) {
			exception.printStackTrace();
		}catch (NamingException namingException){      
			namingException.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn,PreparedStatement ptm,ResultSet rs){
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Connection close error");
			}
		if(ptm!=null)
			try {
				ptm.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement close error");
			}
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("ResultSet close error");
			}
	}

}
