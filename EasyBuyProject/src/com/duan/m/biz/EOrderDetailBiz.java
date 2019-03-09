package com.duan.m.biz;

import java.util.List;
import java.util.Map;

import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.Page;

public interface EOrderDetailBiz {
	//添加订单详情
	public boolean addEOrderDetail(EOrderDetail eod);
}
