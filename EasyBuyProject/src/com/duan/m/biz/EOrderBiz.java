package com.duan.m.biz;

import com.duan.m.entity.EOrder;
import com.duan.m.utils.Page;

public interface EOrderBiz {
	//添加订单
	public boolean addEOrder(EOrder order);
	//查找订单
	public boolean finEOrder(EOrder order);
	//更新订单状态
	public boolean updEOrder(int eo_id,int eo_status);
	//订单分页
	public Page<EOrder> findEOrderPage(int currPageNo);
	//根据订单号、收货人，查找订单
	public Page<EOrder> findEOrderPage2(int entityId,String userName);
}
