package com.duan.m.biz.impl;

import java.sql.Connection;
import java.util.List;

import com.duan.m.biz.EProCateBiz;
import com.duan.m.dao.EProCateDao;
import com.duan.m.dao.impl.EProCateDaoImpl;
import com.duan.m.entity.EProduct_Category;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class EProCateBizImpl implements EProCateBiz {

	@Override
	public Page<EProduct_Category> findProCatePage(int currPageNo) {
		Page<EProduct_Category> page=new Page<EProduct_Category>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		page.setCurrPageNo(currPageNo);
		page.setPageSize(6);
		page.setTotalCount(dao.selectEProCateCount());
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<EProduct_Category> list=dao.selectEProCateList(start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public List<EProduct_Category> findProCateOne() {
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		List<EProduct_Category> list=dao.selectEProCateFather();
		BaseDao.close(conn, null, null);
		return list;
	}

	@Override
	public boolean updProCate(EProduct_Category category) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		b=dao.updateEProCate(category);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean delProCate(int epc_id) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		b=dao.deleteEProCate(epc_id);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean addProCate(EProduct_Category category) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		category.setEpc_id(dao.selectMaxId()+1);
		b=dao.insertEProCate(category);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public List<EProduct_Category> findAllProCate() {
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		List<EProduct_Category> list=dao.selectAllEProCate();
		BaseDao.close(conn, null, null);
		return list;
	}

	@Override
	public EProduct_Category findProCate(int epc_id) {
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EProCateDao dao=new EProCateDaoImpl(conn);
		EProduct_Category cate=dao.selectEProCate(epc_id);
		BaseDao.close(conn, null, null);
		return cate;
	}

}
