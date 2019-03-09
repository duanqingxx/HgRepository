package com.duan.m.biz.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.duan.m.biz.EOrderDetailBiz;
import com.duan.m.dao.EOrderDetailDao;
import com.duan.m.dao.impl.EOrderDetailDaoImpl;
import com.duan.m.entity.EOrder;
import com.duan.m.entity.EOrderDetail;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class EOrderDetailBizImpl implements EOrderDetailBiz {

	@Override
	public boolean addEOrderDetail(EOrderDetail eod) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EOrderDetailDao dao=new EOrderDetailDaoImpl(conn);
		eod.setEod_id(dao.selectMaxId()+1);
		b=dao.insertOrderDetail(eod);
		BaseDao.close(conn, null, null);
		return b;
	}

}
