package com.duan.m.biz.impl;

import java.sql.Connection;
import java.util.List;

import com.duan.m.biz.EProductBiz;
import com.duan.m.dao.EProductDao;
import com.duan.m.dao.impl.EProductDaoImpl;
import com.duan.m.entity.EProduct;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class EProductBizImpl implements EProductBiz {

	@Override
	public Page<EProduct> findEProductPage(int currPageNo) {
		Page<EProduct> page=new Page<EProduct>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		page.setPageSize(4);
		page.setCurrPageNo(currPageNo);
		page.setTotalCount(dao.selectEProductCount());
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<EProduct> list=dao.selectEProductList(start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public boolean addEProduct(EProduct product) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		product.setEp_id(dao.selectMaxId()+1);
		b=dao.insertEProduct(product);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean delEProduct(int ep_id) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		b=dao.deleteEProduct(ep_id);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean updEProduct(EProduct product) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		b=dao.updateEProduct(product);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean finEProduct(EProduct product) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		b=dao.selectEProduct(product);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public List<EProduct> findHotProduct() {
		List<EProduct> list=null;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		list=dao.selectHotProduct();
		BaseDao.close(conn, null, null);
		return list;
	}

	@Override
	public Page<EProduct> findEProductPage2(int currPageNo,int epc_id) {
		Page<EProduct> page=new Page<EProduct>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		page.setPageSize(8);
		page.setCurrPageNo(currPageNo);
		page.setTotalCount(dao.selectEProductCount2(epc_id));
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<EProduct> list=dao.selectEProductList2(epc_id,start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public Page<EProduct> findEProductPage3(int currPageNo, int epc_id2) {
		Page<EProduct> page=new Page<EProduct>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		page.setPageSize(8);
		page.setCurrPageNo(currPageNo);
		page.setTotalCount(dao.selectEProductCount3(epc_id2));
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<EProduct> list=dao.selectEProductList3(epc_id2,start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public boolean updEProduct2(int ep_id, int num) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProductDao dao=new EProductDaoImpl(conn);
		b=dao.updateEProduct2(ep_id, num);
		BaseDao.close(conn, null, null);
		return b;
	}

}
