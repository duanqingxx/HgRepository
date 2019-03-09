package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.m.dao.EUserDao;
import com.duan.m.entity.ENews;
import com.duan.m.entity.UserInfo;
import com.duan.m.utils.BaseDao;

public class EUserDaoImpl implements EUserDao {
	
	private Connection conn=null;
	
	public EUserDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public int selectEUserCount() {
		int count=0;
		String sql="select count(1) count from easybuy_user";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectEUserCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}

	@Override
	public List<UserInfo> selectEUser(int start, int end) {
		List<UserInfo> list=new ArrayList<UserInfo>();
		String sql="SELECT t2.eu_user_id,t2.eu_user_name,"
				+" t2.eu_password,t2.eu_sex,t2.eu_birthday,"
				+" t2.eu_indentity_code,t2.eu_email,t2.eu_mobile,"
				+" t2.eu_address,t2.eu_login,t2.eu_status "
				+" FROM(SELECT ROWNUM rw,t1.* FROM("
				+" SELECT * FROM EASYBUY_USER)t1"
				+" WHERE ROWNUM<=?)t2"
				+" where t2.rw>=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, end);
			ptm.setInt(2, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				UserInfo user=new UserInfo();
				user.setEu_user_id(rs.getString("eu_user_id"));
				user.setEu_user_name(rs.getString("eu_user_name"));
				user.setEu_passWord(rs.getString("eu_passWord"));
				user.setEu_sex(rs.getString("eu_sex"));
				user.setEu_birthday(rs.getTimestamp("eu_birthday"));
				user.setEu_indentity_code(rs.getString("eu_indentity_code"));
				user.setEu_email(rs.getString("eu_email"));
				user.setEu_mobile(rs.getString("eu_mobile"));
				user.setEu_address(rs.getString("eu_address"));
				user.setEu_login(rs.getFloat("eu_login"));
				user.setEu_status(rs.getInt("eu_status"));
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println("selectEUser error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public boolean deleteEUser(String eu_id) {
		boolean b=false;
		String sql="delete from easybuy_user where eu_user_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, eu_id);
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("deleteEUser error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean updataEUser(UserInfo user,String eu_id) {
		boolean b=false;
		String sql="update easybuy_user set eu_user_id=?,"
				+ " eu_user_name=?,eu_password=?,eu_sex=?,"
				+ " eu_birthday=?,"
				+ " eu_mobile=?,eu_address=?"
				+ " where eu_user_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, user.getEu_user_id());
			ptm.setString(2, user.getEu_user_name());
			ptm.setString(3, user.getEu_passWord());
			ptm.setString(4, user.getEu_sex());
			ptm.setTimestamp(5, user.getEu_birthday());
			ptm.setString(6, user.getEu_mobile());
			ptm.setString(7, user.getEu_address());
			ptm.setString(8, eu_id);
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updataEUser error");
			e.printStackTrace();
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

}
