package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.m.dao.EProCateDao;
import com.duan.m.entity.EProduct_Category;
import com.duan.m.utils.BaseDao;

public class EProCateDaoImpl implements EProCateDao {
	private Connection conn=null;
	
	public EProCateDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public List<EProduct_Category> selectEProCateList(int start, int end) {
		List<EProduct_Category> list=new ArrayList<EProduct_Category>();
		String sql="SELECT t2.epc_id,t2.epc_name,t2.epc_id2"
				+ " FROM(SELECT ROWNUM rw,t1.*"
				+ " FROM(SELECT * FROM EASYBUY_PRODUCT_CATEGORY"
				+ " ORDER BY NVL(epc_id2,epc_id),epc_id)t1"
				+ " WHERE ROWNUM<=?)t2"
				+ " WHERE t2.rw>=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, end);
			ptm.setInt(2, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct_Category cate=new EProduct_Category();
				cate.setEpc_id(rs.getInt("epc_id"));
				cate.setEpc_name(rs.getString("epc_name"));
				cate.setEpc_id2(rs.getInt("epc_id2"));
				list.add(cate);
			}
		} catch (SQLException e) {
			System.out.println("EProCateDaoImpl error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}
	@Override
	public int selectEProCateCount() {
		int count=0;
		String sql="select count(1) count from easybuy_product_category";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next())count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectEProCateCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}
	@Override
	public List<EProduct_Category> selectEProCateFather() {
		List<EProduct_Category> list=new ArrayList<EProduct_Category>();
		String sql="SELECT * FROM EASYBUY_PRODUCT_CATEGORY"
				+ " WHERE EPC_ID2 IS NULL";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct_Category category=new EProduct_Category();
				category.setEpc_id(rs.getInt("epc_id"));
				category.setEpc_name(rs.getString("epc_name"));
				category.setEpc_id2(rs.getInt("epc_id2"));
				list.add(category);
			}
		} catch (SQLException e) {
			System.out.println("selectEProCateF");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}
	
	@Override
	public boolean updateEProCate(EProduct_Category category) {
		boolean b=false;
		String sql=null;
		if(category.getEpc_id2()==0)
			sql="UPDATE EASYBUY_PRODUCT_CATEGORY SET EPC_NAME=?"
				+ " WHERE EPC_ID=?";
		else
			sql="UPDATE EASYBUY_PRODUCT_CATEGORY SET EPC_NAME=?,EPC_ID2=?"
					+ " WHERE EPC_ID=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1, category.getEpc_name());
			if(category.getEpc_id2()!=0){
				ptm.setInt(2, category.getEpc_id2());
				ptm.setInt(3, category.getEpc_id());
			}else
				ptm.setInt(2, category.getEpc_id());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updateEProCate error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}
	@Override
	public boolean deleteEProCate(int epc_id) {
		boolean b=false;
		String sql="delete from easybuy_product_category"
				+ " where epc_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, epc_id);
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("deleteEProCate error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}
	@Override
	public int  selectMaxId() {
		int maxid = 0;
		String sql="select max(epc_id) maxid from easybuy_product_category";
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
	public boolean insertEProCate(EProduct_Category category) {
		boolean b=false;
		String sql=null;
		if(category.getEpc_id2()==0)
			sql="INSERT INTO EASYBUY_PRODUCT_CATEGORY VALUES(?,?,NULL)";
		else
			sql="INSERT INTO EASYBUY_PRODUCT_CATEGORY VALUES(?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, category.getEpc_id());
			ptm.setString(2, category.getEpc_name());
			if(category.getEpc_id2()!=0)
				ptm.setInt(3, category.getEpc_id2());
			int i=ptm.executeUpdate();
			if(i>0)b=true;
		} catch (SQLException e) {
			System.out.println("insertEProCate error");
		}finally {
			BaseDao.close(conn, ptm, null);
		}
		return b;
	}
	@Override
	public List<EProduct_Category> selectAllEProCate() {
		List<EProduct_Category> list=new ArrayList<EProduct_Category>();
		String sql="SELECT * FROM EASYBUY_PRODUCT_CATEGORY"
				+ " ORDER BY NVL(epc_id2,epc_id),epc_id";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct_Category category=new EProduct_Category();
				category.setEpc_id(rs.getInt("epc_id"));
				category.setEpc_name(rs.getString("epc_name"));
				category.setEpc_id2(rs.getInt("epc_id2"));
				list.add(category);
			}
		} catch (SQLException e) {
			System.out.println("selectAllEProCate error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}
	@Override
	public EProduct_Category selectEProCate(int epc_id) {
		EProduct_Category cate=new EProduct_Category();
		String sql="select * from easybuy_product_category"
				+ " where epc_id=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, epc_id);
			rs=ptm.executeQuery();
			if(rs.next()){
				cate.setEpc_id(rs.getInt("epc_id"));
				cate.setEpc_name(rs.getString("epc_name"));
				cate.setEpc_id2(rs.getInt("epc_id2"));
			}
		} catch (SQLException e) {
			System.out.println("EProduct_Category error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return cate;
	}


}
