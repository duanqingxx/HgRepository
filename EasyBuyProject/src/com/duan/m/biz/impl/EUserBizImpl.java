package com.duan.m.biz.impl;

import java.sql.Connection;
import java.util.List;

import com.duan.m.biz.EUserBiz;
import com.duan.m.dao.EUserDao;
import com.duan.m.dao.impl.EUserDaoImpl;
import com.duan.m.entity.UserInfo;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;
import com.duan.m.utils.Page;

public class EUserBizImpl implements EUserBiz {

	@Override
	public Page<UserInfo> findEUserPage(int currPageNo) {
		Page<UserInfo> page=new Page<UserInfo>();
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EUserDao dao=new EUserDaoImpl(conn);
		page.setPageSize(2);
		page.setCurrPageNo(currPageNo);
		page.setTotalCount(dao.selectEUserCount());
		int start,end;
		start=(currPageNo-1)*page.getPageSize()+1;
		end=currPageNo*page.getPageSize();
		List<UserInfo> list=dao.selectEUser(start, end);
		page.setNewsList(list);
		BaseDao.close(conn, null, null);
		return page;
	}

	@Override
	public boolean updEUser(UserInfo user, String eu_id) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EUserDao dao=new EUserDaoImpl(conn);
		b=dao.updataEUser(user, eu_id);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean delEUser(String eu_id) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		EUserDao dao=new EUserDaoImpl(conn);
		b=dao.deleteEUser(eu_id);
		BaseDao.close(conn, null, null);
		return b;
	}

}
