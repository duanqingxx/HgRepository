package com.duan.m.biz.impl;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.duan.m.biz.ECommBiz;
import com.duan.m.dao.ECommDao;
import com.duan.m.dao.impl.ECommDaoImpl;
import com.duan.m.entity.EComment;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class ECommBizImpl implements ECommBiz {

	@Override
	public Page<EComment> findECommPage(int currPageNo) {
		Page<EComment> page=new Page<EComment>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ECommDao dao=new ECommDaoImpl(conn);
		page.setPageSize(3);
		page.setCurrPageNo(currPageNo);
		page.setTotalCount(dao.selectCommCount());
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<EComment> list=dao.selectCommList(start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public boolean addEComment(EComment comment) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ECommDao dao=new ECommDaoImpl(conn);
		comment.setEc_id(dao.selectMaxId()+1);
		comment.setEc_create_time(new Timestamp(new Date().getTime()));
		b=dao.insertComm(comment);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean delEComment(int ec_id) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ECommDao dao=new ECommDaoImpl(conn);
		b=dao.deleteComm(ec_id);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean updEComment(EComment comment) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		ECommDao dao=new ECommDaoImpl(conn);
		comment.setEc_reply_time(new Timestamp(new Date().getTime()));
		b=dao.updateComm(comment);
		BaseDao.close(conn, null, null);
		return b;
	}

}
