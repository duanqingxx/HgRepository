package com.duan.m.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.duan.m.dao.EOrderDetailDao;
import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.BaseDao;

public class EOrderDetailDaoImpl implements EOrderDetailDao {
	
	private Connection conn=null;
	
	public EOrderDetailDaoImpl(Connection conn){
		this.conn=conn;
	}
	@Override
	public int selectMaxId() {
		int maxid = 0;
		String sql="select max(eod_id) maxid from easybuy_order_detail";
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
	public boolean insertOrderDetail(EOrderDetail orderDetail) {
		boolean b=false;
		String sql="insert into easybuy_order_detail values(?,?,?,?,?)";
		PreparedStatement ptm=null;
		try {
			ptm=conn.prepareStatement(sql);
			ptm.setInt(1, orderDetail.getEod_id());
			ptm.setInt(2, orderDetail.getEo_id());
			ptm.setInt(3, orderDetail.getEp_id());
			ptm.setInt(4, orderDetail.getEod_quantity());
			ptm.setFloat(5, orderDetail.getEod_cost());
			int i=ptm.executeUpdate();
			if(i>0) b=true;
		} catch (SQLException e) {
			System.out.println("insertOrderDetail error");
		}finally {
			BaseDao.close(null, ptm, null);
		}
		return  b;
	}
	
//	@Override
//	public List<EOrderDetail> selectEODList(int start, int end,
//			List<EOrder> orderList,Map<String,EProduct> map) {
//		List<EOrderDetail> list=new ArrayList<EOrderDetail>();
//		String sql="SELECT t2.* FROM(SELECT ROWNUM rw,T1.* FROM(SELECT E2.EOD_ID,E2.EO_ID,E2.EP_ID,E2.EOD_QUANTITY,"
//				 +" E2.EOD_COST,E1.EO_CREATE_TIME,E1.EO_COST,E1.EO_STATUS,"
//				 +" P1.EP_NAME,P1.EP_PRICE FROM EASYBUY_ORDER_DETAIL e2,"
//				 +" EASYBUY_ORDER e1,EASYBUY_PRODUCT p1"
//				 +" WHERE e2.eo_id=e1.eo_id AND e2.ep_id=p1.ep_id"
//				 +" ORDER BY E2.EO_ID)t1"
//				 +" WHERE ROWNUM<=?)t2"
//				 +" WHERE t2.rw>=?";
//		PreparedStatement ptm=null;
//		ResultSet rs=null;
//		try {
//			ptm=conn.prepareStatement(sql);
//			ptm.setInt(1, end);
//			ptm.setInt(2, start);
//			rs=ptm.executeQuery();
//			EOrder order=new EOrder();
//			EProduct product=null;
//			while(rs.next()){
//				EOrderDetail eod=new EOrderDetail();
//				eod.setEod_id(rs.getInt("eod_id"));
//				eod.setEo_id(rs.getInt("eo_id"));
//				eod.setEp_id(rs.getInt("ep_id"));
//				eod.setEod_quantity(rs.getInt("eod_quantity"));
//				eod.setEod_cost(rs.getFloat("eod_cost"));
//				list.add(eod);
//				if(order.getEo_id()!=eod.getEo_id()){
//					order=new EOrder();
//					order.setEo_id(rs.getInt("eo_id"));
//					order.setEo_create_time(rs.getTimestamp("eo_create_time"));
//					order.setEo_cost(rs.getFloat("eo_cost"));
//					order.setEo_status(rs.getInt("eo_status"));
//					orderList.add(order);
//					}
//				if(map.get(String.valueOf(eod.getEp_id()))==null){
//					product=new EProduct();
//					product.setEp_id(rs.getInt("ep_id"));
//					product.setEp_price(rs.getFloat("ep_price"));
//					product.setEp_name(rs.getString("ep_name"));
//					map.put(String.valueOf(eod.getEp_id()), product);
//					}
//				
//			}
//		} catch (SQLException e) {
//			System.out.println("selectEODList error");
//		}finally {
//			BaseDao.close(null, ptm, rs);
//		}
//		return list;
//	}
//	@Override
//	public int selectEODCount() {
//		int count=0;
//		String sql="SELECT count(1) count FROM EASYBUY_ORDER_DETAIL";
//		PreparedStatement ptm=null;
//		ResultSet rs=null;
//		try {
//			ptm=conn.prepareStatement(sql);
//			rs=ptm.executeQuery();
//			if(rs.next()) count=rs.getInt("count");
//		} catch (SQLException e) {
//			System.out.println("selectEODCount error");
//		}finally {
//			BaseDao.close(null, ptm, rs);
//		}
//		return count;
//	}

}
