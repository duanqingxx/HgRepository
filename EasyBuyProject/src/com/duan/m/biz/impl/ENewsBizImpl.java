package com.duan.m.biz.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.duan.m.biz.ENewsBiz;
import com.duan.m.dao.ENewsDao;
import com.duan.m.dao.impl.ENewsDaoImpl;
import com.duan.m.entity.ENews;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class ENewsBizImpl implements ENewsBiz {

	@Override
	public List<ENews> findHotNews() {
		List<ENews> list=null;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ENewsDao dao=new ENewsDaoImpl(conn);
		list=dao.selectHotNews();
		BaseDao.close(conn, null, null);
		return list;
	}

	@Override
	public ENews findENewsDetail(int en_id) {
		ENews news=null;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ENewsDao dao=new ENewsDaoImpl(conn);
		news=dao.selectENewsDetail(en_id);
		BaseDao.close(conn, null, null);
		return news;
	}

	@Override
	public Page<ENews> findENewsPage(int currPageNo) {
		Page<ENews> page=new Page<ENews>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ENewsDao dao=new ENewsDaoImpl(conn);
		page.setPageSize(5);
		page.setCurrPageNo(currPageNo);
		page.setTotalCount(dao.selectENewsCount());
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<ENews> list=dao.selectENews(start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public boolean addENews(ENews news) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ENewsDao dao=new ENewsDaoImpl(conn);
		news.setEn_id(dao.selectMaxId()+1);
		news.setEn_create_time(new Timestamp(new Date().getTime()));
		b=dao.insertENews(news);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean updENews(ENews news) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ENewsDao dao=new ENewsDaoImpl(conn);
		b=dao.updataENews(news);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean delENews(int en_id) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ENewsDao dao=new ENewsDaoImpl(conn);
		b=dao.deleteENews(en_id);
		BaseDao.close(conn, null, null);
		return b;
	}

}
