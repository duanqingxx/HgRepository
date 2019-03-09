package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.PredicatedMap;

import com.duan.m.dao.EProductDao;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.BaseDao;

public class EProductDaoImpl implements EProductDao {
	private Connection conn=null;
	
	public EProductDaoImpl(Connection conn){
		this.conn=conn;
	}
	
	@Override
	public int selectEProductCount() {
		int count=0;
		String sql="SELECT count(1) count FROM EASYBUY_PRODUCT";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectEProductCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}

	@Override
	public List<EProduct> selectEProductList(int start, int end) {
		List<EProduct> list=new ArrayList<EProduct>();
		String sql="SELECT T2.EP_ID,T2.EP_NAME,T2.EP_DESCRIPTION,T2.EP_PRICE,"
				+ " T2.EP_STOCK,T2.EPC_ID,T2.EPC_ID2,T2.EP_FILE_NAME"
				+ " FROM (SELECT ROWNUM RW,t1.* FROM"
				+ " (SELECT * FROM EASYBUY_PRODUCT)t1"
				+ " WHERE ROWNUM<=?)t2"
				+ " WHERE T2.rw>=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, end);
			ptm.setInt(2, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct product=new EProduct();
				product.setEp_id(rs.getInt("ep_id"));
				product.setEp_name(rs.getString("ep_name"));
				product.setEp_description(rs.getString("ep_description"));
				product.setEp_price(rs.getFloat("ep_price"));
				product.setEp_stock(rs.getInt("ep_stock"));
				product.setEpc_id(rs.getInt("epc_id"));
				product.setEpc_id2(rs.getInt("epc_id2"));
				product.setEp_file_name(rs.getString("ep_file_name"));
				list.add(product);
			}
		} catch (SQLException e) {
			System.out.println("selectEProductList error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public int selectMaxId() {
		int maxid = 0;
		String sql="select max(ep_id) maxid from easybuy_product";
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
	public boolean insertEProduct(EProduct product) {
		boolean b=false;
		String sql="insert into easybuy_product values(?,?,?,?,?,?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, product.getEp_id());
			ptm.setString(2, product.getEp_name());
			ptm.setString(3, product.getEp_description());
			ptm.setFloat(4, product.getEp_price());
			ptm.setInt(5,product.getEp_stock());
			ptm.setInt(6, product.getEpc_id());
			ptm.setInt(7, product.getEpc_id2());
			ptm.setString(8, product.getEp_file_name());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("insertEProduct error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return  b;
	}

	@Override
	public boolean updateEProduct(EProduct product) {
		boolean b=false;
		String sql=" UPDATE EASYBUY_PRODUCT SET ep_name=?,ep_description=?,"
				+ "  ep_price=?,ep_stock=?,epc_id=?,epc_id2=?,ep_file_name=?"
				+ "  where ep_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setString(1,product.getEp_name());
			ptm.setString(2,product.getEp_description());
			ptm.setFloat(3, product.getEp_price());
			ptm.setInt(4, product.getEp_stock());
			ptm.setInt(5, product.getEpc_id());
			ptm.setInt(6, product.getEpc_id2());
			ptm.setString(7, product.getEp_file_name());
			ptm.setInt(8, product.getEp_id());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updateEProduct error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean deleteEProduct(int ep_id) {
		boolean b=false;
		String sql="delete from easybuy_product where ep_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, ep_id);
			int i=ptm.executeUpdate();
			if(i>0)b=true;
		} catch (SQLException e) {
			System.out.println("deleteEProduct error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

	@Override
	public boolean selectEProduct(EProduct product) {
		boolean b=false;
		String sql="select * from easybuy_product where ep_id=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, product.getEp_id());
			rs=ptm.executeQuery();
			if(rs.next()){
				b=true;
				product.setEp_name(rs.getString("ep_name"));
				product.setEp_description(rs.getString("ep_description"));
				product.setEp_price(rs.getFloat("ep_price"));
				product.setEp_stock(rs.getInt("ep_stock"));
				product.setEpc_id(rs.getInt("epc_id"));
				product.setEpc_id2(rs.getInt("epc_id2"));
				product.setEp_file_name(rs.getString("ep_file_name"));
			}
		} catch (SQLException e) {
			System.out.println("selectEProduct error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return b;
	}

	@Override
	public List<EProduct> selectHotProduct() {
		List<EProduct> list=new ArrayList<EProduct>();
		String sql="SELECT * FROM("
				+ " SELECT * FROM EASYBUY_PRODUCT"
				+ " ORDER BY EP_ID ASC)"
				+ " WHERE ROWNUM<=8";
//		String sql="SELECT * FROM EASYBUY_PRODUCT"
//				+ " WHERE ROWNUM <=8"
//				+ " ORDER BY ep_id DESC";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct product=new EProduct();
				product.setEp_id(rs.getInt("ep_id"));
				product.setEp_name(rs.getString("ep_name"));
				product.setEp_description(rs.getString("ep_description"));
				product.setEp_price(rs.getFloat("ep_price"));
				product.setEp_stock(rs.getInt("ep_stock"));
				product.setEpc_id(rs.getInt("epc_id"));
				product.setEpc_id2(rs.getInt("epc_id2"));
				product.setEp_file_name(rs.getString("ep_file_name"));
				list.add(product);
			}
		} catch (SQLException e) {
			System.out.println("selectHotProduct error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public int selectEProductCount2(int epc_id) {
		int count=0;
		String sql="SELECT count(1) count FROM EASYBUY_PRODUCT WHERE EPC_ID=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, epc_id);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectEProductCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}
	
	@Override
	public List<EProduct> selectEProductList2(int epc_id
			, int start, int end) {
		List<EProduct> list=new ArrayList<EProduct>();
		String sql="SELECT T2.EP_ID,T2.EP_NAME,T2.EP_DESCRIPTION,T2.EP_PRICE,"
				+ " T2.EP_STOCK,T2.EPC_ID,T2.EPC_ID2,T2.EP_FILE_NAME"
				+ " FROM (SELECT ROWNUM RW,t1.* FROM"
				+ " (SELECT * FROM EASYBUY_PRODUCT WHERE EPC_ID=?)t1"
				+ " WHERE ROWNUM<=?)t2"
				+ " WHERE T2.rw>=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, epc_id);
			ptm.setInt(2, end);
			ptm.setInt(3, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct product=new EProduct();
				product.setEp_id(rs.getInt("ep_id"));
				product.setEp_name(rs.getString("ep_name"));
				product.setEp_description(rs.getString("ep_description"));
				product.setEp_price(rs.getFloat("ep_price"));
				product.setEp_stock(rs.getInt("ep_stock"));
				product.setEpc_id(rs.getInt("epc_id"));
				product.setEpc_id2(rs.getInt("epc_id2"));
				product.setEp_file_name(rs.getString("ep_file_name"));
				list.add(product);
			}
		} catch (SQLException e) {
			System.out.println("selectEProductList2 error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public int selectEProductCount3(int epc_id2) {
		int count=0;
		String sql="SELECT count(1) count FROM EASYBUY_PRODUCT WHERE EPC_ID2=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, epc_id2);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectEProductCount3 error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}

	@Override
	public List<EProduct> selectEProductList3(int epc_id2,
			int start, int end) {
		List<EProduct> list=new ArrayList<EProduct>();
		String sql="SELECT T2.EP_ID,T2.EP_NAME,T2.EP_DESCRIPTION,T2.EP_PRICE,"
				+ " T2.EP_STOCK,T2.EPC_ID,T2.EPC_ID2,T2.EP_FILE_NAME"
				+ " FROM (SELECT ROWNUM RW,t1.* FROM"
				+ " (SELECT * FROM EASYBUY_PRODUCT WHERE EPC_ID2=?)t1"
				+ " WHERE ROWNUM<=?)t2"
				+ " WHERE T2.rw>=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, epc_id2);
			ptm.setInt(2, end);
			ptm.setInt(3, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				EProduct product=new EProduct();
				product.setEp_id(rs.getInt("ep_id"));
				product.setEp_name(rs.getString("ep_name"));
				product.setEp_description(rs.getString("ep_description"));
				product.setEp_price(rs.getFloat("ep_price"));
				product.setEp_stock(rs.getInt("ep_stock"));
				product.setEpc_id(rs.getInt("epc_id"));
				product.setEpc_id2(rs.getInt("epc_id2"));
				product.setEp_file_name(rs.getString("ep_file_name"));
				list.add(product);
			}
		} catch (SQLException e) {
			System.out.println("selectEProductList3 error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

	@Override
	public boolean updateEProduct2(int ep_id, int num) {
		boolean b=false;
		String sql="UPDATE EASYBUY_PRODUCT SET EP_STOCK=EP_STOCK-?"
				+ " WHERE EP_ID=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, num);
			ptm.setInt(2, ep_id);
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updateEProduct2 error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}

}
