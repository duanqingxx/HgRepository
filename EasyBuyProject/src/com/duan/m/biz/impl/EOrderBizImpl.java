package com.duan.m.biz.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.duan.m.biz.EOrderBiz;
import com.duan.m.dao.EOrderDao;
import com.duan.m.dao.impl.EOrderDaoImpl;
import com.duan.m.entity.EOrder;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class EOrderBizImpl implements EOrderBiz {

	@Override
	public boolean addEOrder(EOrder order) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EOrderDao dao=new EOrderDaoImpl(conn);
		order.setEo_id(dao.selectMaxId()+1);
		order.setEo_create_time(new Timestamp(new Date().getTime()));
		b=dao.insertOrder(order);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean finEOrder(EOrder order) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EOrderDao dao=new EOrderDaoImpl(conn);
		b=dao.selectEOrder(order);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean updEOrder( int eo_id,int eo_status) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EOrderDao dao=new EOrderDaoImpl(conn);
		b=dao.updateEOrder(eo_id, eo_status);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public Page<EOrder> findEOrderPage(int currPageNo) {
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EOrderDao dao=new EOrderDaoImpl(conn);
		
		Page<EOrder> page=new Page<EOrder>();
		page.setCurrPageNo(currPageNo);
		page.setPageSize(3);
		page.setTotalCount(dao.selectEOrderCount());
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<EOrder> list=dao.selectEOrderList(start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public Page<EOrder> findEOrderPage2(int entityId, String userName) {
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EOrderDao dao=new EOrderDaoImpl(conn);
		
		Page<EOrder> page=new Page<EOrder>();
		page.setCurrPageNo(1);
		page.setPageSize(1);
		page.setTotalCount(1);

		List<EOrder> list=dao.selectEOrderList2(entityId, userName);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

}
