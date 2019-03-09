package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.duan.m.dao.UserInfoDao;
import com.duan.m.entity.UserInfo;
import com.duan.m.utils.BaseDao;

public class UserInfoDaoImpl implements UserInfoDao {
	Connection conn=null;
	
	public UserInfoDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public boolean selectUser(UserInfo user) {
		boolean b=false;	
		String sql="select * from easybuy_user where eu_user_id=?"
				+ " and eu_password=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, user.getEu_user_id());
			ptm.setString(2, user.getEu_passWord());
			rs=ptm.executeQuery();
			if(rs.next()){
				b=true;
				user.setEu_user_name(rs.getString("eu_user_name"));
				user.setEu_sex(rs.getString("eu_sex"));
				user.setEu_birthday(rs.getTimestamp("eu_birthday"));
				user.setEu_indentity_code(rs.getString("eu_indentity_code"));
				user.setEu_email(rs.getString("eu_email"));
				user.setEu_mobile(rs.getString("eu_mobile"));
				user.setEu_address(rs.getString("eu_address"));
				user.setEu_login(rs.getFloat("eu_login"));
				user.setEu_status(rs.getInt("eu_status"));
			}
		} catch (SQLException e) {
			System.out.println("selectUser error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return b;
	}

	@Override
	public boolean insertUser(UserInfo user) {
		boolean b=false;
		String sql=" insert into easybuy_user "
				+ "  values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, user.getEu_user_id());
			ptm.setString(2, user.getEu_user_name());
			ptm.setString(3, user.getEu_passWord());
			ptm.setString(4, user.getEu_sex());
			ptm.setTimestamp(5, user.getEu_birthday());
			ptm.setString(6, user.getEu_indentity_code());
			ptm.setString(7, user.getEu_email());
			ptm.setString(8, user.getEu_mobile());
			ptm.setString(9, user.getEu_address());
			ptm.setFloat(10, user.getEu_login());
			ptm.setInt(11, user.getEu_status());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("insertUser error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean selectUserId(String userId) {
		boolean b=false;
		int num = 0;
		String sql="select count(1) num from easybuy_user where eu_user_id=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, userId);
			rs=ptm.executeQuery();
			if(rs.next())  
				 num= rs.getInt("num");
			if(num!=0) b=true;
		} catch (SQLException e) {
			System.out.println("selectUserId error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return b;
	}

}
