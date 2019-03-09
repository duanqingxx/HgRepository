package com.duan.m.biz.impl;

import java.sql.Connection;

import com.duan.m.biz.UserInfoBiz;
import com.duan.m.dao.UserInfoDao;
import com.duan.m.dao.impl.UserInfoDaoImpl;
import com.duan.m.entity.UserInfo;
import com.duan.m.utils.BaseDao;
import com.duan.m.utils.Dbutils;

import oracle.jdbc.driver.DBConversion;

public class UserInfoBizImpl implements UserInfoBiz {

	@Override
	public boolean findUser(UserInfo user) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		UserInfoDao dao=new UserInfoDaoImpl(conn);
		b=dao.selectUser(user);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean addUser(UserInfo user) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		UserInfoDao dao=new UserInfoDaoImpl(conn);
		b=dao.insertUser(user);
		BaseDao.close(conn, null, null);
		return b;
	}

	@Override
	public boolean findUserById(String userId) {
		boolean b=false;
		BaseDao baseDao=new BaseDao();
		Connection conn=baseDao.getConnection();
//		Connection conn=Dbutils.getConnection();
		UserInfoDao dao=new UserInfoDaoImpl(conn);
		b=dao.selectUserId(userId);
		BaseDao.close(conn, null, null);
		return b;
	}

}
