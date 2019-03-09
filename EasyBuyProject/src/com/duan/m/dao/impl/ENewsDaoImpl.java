package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.m.dao.ENewsDao;
import com.duan.m.entity.ENews;
import com.duan.m.utils.BaseDao;

public class ENewsDaoImpl implements ENewsDao {
	
	private Connection conn=null;
	
	public ENewsDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public List<ENews> selectHotNews() {
		List<ENews> list=new ArrayList<ENews>();
		String sql="SELECT * FROM(select * from EASYBUY_NEWS"
				+ " ORDER BY en_create_time desc)"
				+ " WHERE ROWNUM<=15";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			while(rs.next()){
				ENews news=new ENews();
				news.setEn_id(rs.getInt("en_id"));
				news.setEn_title(rs.getString("en_title"));
				news.setEn_content(rs.getString("en_content"));
				news.setEn_create_time(rs.getTimestamp("en_create_time"));
				list.add(news);
			}
		} catch (SQLException e) {
			System.out.println("selectAllNews error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public ENews selectENewsDetail(int en_id) {
		ENews news=null;
		String sql="select * from easybuy_news where en_id=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, en_id);
			rs=ptm.executeQuery();
			if(rs.next()){
				news=new ENews();
				news.setEn_id(en_id);
				news.setEn_title(rs.getString("en_title"));
				news.setEn_content(rs.getString("en_content"));
				news.setEn_create_time(rs.getTimestamp("en_create_time"));
			}
		} catch (SQLException e) {
			System.out.println("selectENewsDetail error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return news;
	}

	@Override
	public List<ENews> selectENews(int start, int end) {
		List<ENews> list=new ArrayList<ENews>();
		String sql="SELECT T2.en_id,T2.en_title,T2.en_content,T2.en_create_time"
				 +" FROM (SELECT ROWNUM RW,t1.* FROM"
				 +" (SELECT * FROM EASYBUY_NEWS ORDER BY en_create_time DESC)t1"
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
				ENews news=new ENews();
				news.setEn_id(rs.getInt("en_id"));
				news.setEn_title(rs.getString("en_title"));
				news.setEn_content(rs.getString("en_content"));
				news.setEn_create_time(rs.getTimestamp("en_create_time"));
				list.add(news);
			}
		} catch (SQLException e) {
			System.out.println("selectNews error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public int selectENewsCount() {
		int count=0;
		String sql="SELECT count(1) count FROM EASYBUY_NEWS";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectENewsCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}

	@Override
	public boolean insertENews(ENews news) {
		boolean b=false;
		String sql="insert into easybuy_news values(?,?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, news.getEn_id());
			ptm.setString(2, news.getEn_title());
			ptm.setString(3, news.getEn_content());
			ptm.setTimestamp(4, news.getEn_create_time());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("insertENews error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return  b;
	}

	@Override
	public boolean deleteENews(int en_id) {
		boolean b=false;
		String sql="delete from easybuy_news where en_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, en_id);
			int i=ptm.executeUpdate();
			if(i>0)b=true;
		} catch (SQLException e) {
			System.out.println("deleteENews error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean updataENews(ENews news) {
		boolean b=false;
		String sql=" UPDATE easybuy_news set en_title=?,en_content=?"
				+ "  where en_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, news.getEn_title());
			ptm.setString(2, news.getEn_content());
			ptm.setInt(3, news.getEn_id());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updataENews error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public int selectMaxId() {
		int maxid = 0;
		String sql="select max(en_id) maxid from easybuy_news";
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

}
