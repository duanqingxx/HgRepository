package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duan.m.dao.EOrderDao;
import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.BaseDao;

public class EOrderDaoImpl implements EOrderDao {
	private Connection conn=null;
	
	public EOrderDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public int selectMaxId() {
		int maxid = 0;
		String sql="select max(eo_id) maxid from easybuy_order";
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
	public boolean insertOrder(EOrder order) {
		boolean b=false;
		String sql="insert into easybuy_order values(?,?,?,?,?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, order.getEo_id());
			ptm.setString(2,order.getEo_user_id());
			ptm.setString(3, order.getEo_user_name());
			ptm.setString(4, order.getEo_user_address());
			ptm.setTimestamp(5, order.getEo_create_time());
			ptm.setFloat(6, order.getEo_cost());
			ptm.setInt(7, order.getEo_status());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("insertOrder error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return  b;
	}
	@Override
	public boolean selectEOrder(EOrder order) {
		boolean b=false;
		String sql="select * from easybuy_order where eo_id=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, order.getEo_id());
			rs=ptm.executeQuery();
			if(rs.next()){
				b=true;
				order.setEo_user_id(rs.getString("eo_user_id"));
				order.setEo_user_name(rs.getString("eo_user_name"));
				order.setEo_user_address(rs.getString("eo_user_address"));
				order.setEo_create_time(rs.getTimestamp("eo_create_time"));
				order.setEo_cost(rs.getFloat("eo_cost"));
				order.setEo_status(rs.getInt("eo_status"));
			}
		} catch (SQLException e) {
			System.out.println("selectEOrder error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return b;
	}
	@Override
	public boolean updateEOrder(int eo_id, int eo_status) {
		boolean b=false;
		String sql="update easybuy_order set eo_status=? where eo_id=?";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, eo_status);
			ptm.setInt(2, eo_id);
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("updateEOrder error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return b;
	}
	@Override
	public int selectEOrderCount() {
		int count=0;
		String sql="SELECT count(1) count FROM EASYBUY_ORDER";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			rs=ptm.executeQuery();
			if(rs.next()) count=rs.getInt("count");
		} catch (SQLException e) {
			System.out.println("selectEOrderCount error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return count;
	}
	@Override
	public List<EOrder> selectEOrderList(int start, int end) {
		List<EOrder> list=new ArrayList<EOrder>();
		String sql="SELECT T2.eo_id,T2.eo_create_time,"
				+ " T2.eo_cost,T2.eo_status FROM(SELECT ROWNUM rw,T1.*"
				+ " FROM(SELECT * FROM EASYBUY_ORDER ORDER BY EO_ID)t1"
				+ " WHERE ROWNUM<=?)t2"
				+ " WHERE T2.rw>=?";
		String sql2="SELECT E1.EOD_ID,E1.EO_ID,E1.EP_ID,E1.EOD_QUANTITY,"
				+ " E1.EOD_COST,P1.EP_PRICE,P1.EP_NAME FROM EASYBUY_ORDER_DETAIL e1,EASYBUY_PRODUCT p1"
				+ " WHERE E1.EP_ID=P1.EP_ID AND E1.EO_ID=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, end);
			ptm.setInt(2, start);
			rs=ptm.executeQuery();
			while(rs.next()){
				EOrder order=new EOrder();
				List<EOrderDetail> eodList=new ArrayList<EOrderDetail>();
				order.setEo_id(rs.getInt("eo_id"));
				order.setEo_create_time(rs.getTimestamp("eo_create_time"));
				order.setEo_cost(rs.getFloat("eo_cost"));
				order.setEo_status(rs.getInt("eo_status"));
				ptm=conn.prepareStatement(sql2);
				ptm.setInt(1, order.getEo_id());
				rs2=ptm.executeQuery();
				while(rs2.next()){
					EOrderDetail eod=new EOrderDetail();
					EProduct product=new EProduct();
					eod.setEod_id(rs2.getInt("eod_id"));
					eod.setEo_id(rs2.getInt("eo_id"));
					eod.setEp_id(rs2.getInt("ep_id"));
					eod.setEod_quantity(rs2.getInt("eod_quantity"));
					eod.setEod_cost(rs2.getFloat("eod_cost"));
					product.setEp_id(rs2.getInt("ep_id"));
					product.setEp_name(rs2.getString("ep_name"));
					product.setEp_price(rs2.getFloat("ep_price"));
					eod.setProduct(product);
					eodList.add(eod);
				}
				order.setEodList(eodList);
				list.add(order);
			}
		} catch (SQLException e) {
			System.out.println("selectEOrderList error");
		}finally{
			BaseDao.close(null, ptm, rs);
			BaseDao.close(null, null, rs2);
		}
		return list;
	}
	@Override
	public List<EOrder> selectEOrderList2(int entityId,String userName) {
		List<EOrder> list=new ArrayList<EOrder>();
		String sql="SELECT E2.EOD_ID,E2.EO_ID,E2.EP_ID,E2.EOD_QUANTITY,"
				+ " E2.EOD_COST,E1.EO_CREATE_TIME,E1.EO_COST,E1.EO_STATUS,"
				+ " P1.EP_NAME,P1.EP_PRICE FROM EASYBUY_ORDER_DETAIL e2,"
				+ " EASYBUY_ORDER e1,EASYBUY_PRODUCT p1"
				+ " WHERE e2.eo_id=e1.eo_id AND e2.ep_id=p1.ep_id"
				+ " AND e1.eo_id=? AND e1.eo_user_name=?";
		PreparedStatement ptm=null;
		ResultSet rs=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, entityId);
			ptm.setString(2, userName);
			rs=ptm.executeQuery();
			List<EOrderDetail> eodList=new ArrayList<EOrderDetail>();
			EOrder order=new EOrder();
			while(rs.next()){
				EOrderDetail eod=new EOrderDetail();
				EProduct product=new EProduct();
				eod.setEod_id(rs.getInt("eod_id"));
				eod.setEo_id(rs.getInt("eo_id"));
				eod.setEp_id(rs.getInt("ep_id"));
				eod.setEod_quantity(rs.getInt("eod_quantity"));
				eod.setEod_cost(rs.getFloat("eod_cost"));
				product.setEp_id(rs.getInt("ep_id"));
				product.setEp_name(rs.getString("ep_name"));
				product.setEp_price(rs.getFloat("ep_price"));
				eod.setProduct(product);
				eodList.add(eod);
				order.setEo_id(rs.getInt("eo_id"));
				order.setEo_create_time(rs.getTimestamp("eo_create_time"));
				order.setEo_cost(rs.getFloat("eo_cost"));
				order.setEo_status(rs.getInt("eo_status"));
			}
			order.setEodList(eodList);
			list.add(order);
		} catch (SQLException e) {
			System.out.println("selectEOrderList2 error");
		}finally {
			BaseDao.close(null, ptm, rs);
		}
		return list;
	}

}
