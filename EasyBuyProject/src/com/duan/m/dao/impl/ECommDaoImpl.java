package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.m.dao.ECommDao;
import com.duan.m.entity.EComment;
import com.duan.m.utils.BaseDao;

public class ECommDaoImpl implements ECommDao {
	
	private Connection conn=null;
	
	public ECommDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public int selectCommCount() {
		int count=0;
		String sql="select count(1) count from easybuy_comment";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectCommCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}

	@Override
	public List<EComment> selectCommList(int start, int end) {
		List<EComment> list=new ArrayList<EComment>();
		String sql="SELECT T2.ec_id,T2.ec_content,T2.ec_create_time,"
				+" T2.ec_reply,T2.ec_reply_time,T2.ec_nick_name"
				+" FROM(SELECT ROWNUM rw,T1.* FROM"
				+" (SELECT * FROM EASYBUY_COMMENT ORDER BY EC_CREATE_TIME DESC)t1"
				+" WHERE ROWNUM<=?)t2"
				+" WHERE T2.rw>=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, end);
			ptm.setInt(2, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				EComment comment=new EComment();
				comment.setEc_id(rs.getInt("ec_id"));
				comment.setEc_content(rs.getString("ec_content"));
				comment.setEc_create_time(rs.getTimestamp("ec_create_time"));
				comment.setEc_reply(rs.getString("ec_reply"));
				comment.setEc_reply_time(rs.getTimestamp("ec_reply_time"));
				comment.setEc_nick_name(rs.getString("ec_nick_name"));
				list.add(comment);
			}
		} catch (SQLException e) {
			System.out.println("selectCommList error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public int selectMaxId() {
		int maxid=0;
		String sql="select max(ec_id) maxid from easybuy_comment";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next()) maxid=rs.getInt("maxid");
		} catch (SQLException e) {
			System.out.println("selectMaxId error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return maxid;
	}

	@Override
	public boolean insertComm(EComment comment) {
		boolean b=false;
		String sql="INSERT INTO EASYBUY_COMMENT"
				+ " (EC_ID,EC_CONTENT,EC_CREATE_TIME,EC_NICK_NAME)"
				+ "  VALUES(?,?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, comment.getEc_id());
			ptm.setString(2, comment.getEc_content());
			ptm.setTimestamp(3, comment.getEc_create_time());
			ptm.setString(4, comment.getEc_nick_name());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("insertComm error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean deleteComm(int ec_id) {
		boolean b=false;
		String sql="delete from easybuy_comment where ec_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, ec_id);
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("deleteComm error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean updateComm(EComment comment) {
		boolean b=false;
		String sql="update easybuy_comment set ec_reply=?,"
				+ " ec_reply_time=?"
				+ " where ec_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, comment.getEc_reply());
			ptm.setTimestamp(2, comment.getEc_reply_time());
			ptm.setInt(3, comment.getEc_id());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updateComm error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

}
