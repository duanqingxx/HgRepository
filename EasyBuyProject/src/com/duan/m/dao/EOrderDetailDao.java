package com.duan.m.dao;

import java.util.List;
import java.util.Map;

import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;

public interface EOrderDetailDao {
	public int     selectMaxId();
	public boolean insertOrderDetail(EOrderDetail orderDetail);
	
//	public int selectEODCount();
//	public List<EOrderDetail> selectEODList(int start,int end,
//			List<EOrder> orderList,Map<String,EProduct> map);
	
}
