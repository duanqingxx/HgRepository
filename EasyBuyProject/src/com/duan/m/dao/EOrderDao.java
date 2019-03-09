package com.duan.m.dao;

import java.util.List;

import com.duan.m.entity.EOrder;

public interface EOrderDao {
	public int     selectMaxId();
	public boolean insertOrder(EOrder order);
	public boolean selectEOrder(EOrder order);
	public boolean updateEOrder(int eo_id,int eo_status);
	
	//以父订单为单位进行分页
	public int          selectEOrderCount();
	public List<EOrder> selectEOrderList(int start,int end);
	public List<EOrder> selectEOrderList2(int entityId,String userName);
}
