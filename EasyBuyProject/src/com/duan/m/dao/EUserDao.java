package com.duan.m.dao;

import java.util.List;

import com.duan.m.entity.UserInfo;

public interface EUserDao {
	public int             selectEUserCount();
	public List<UserInfo>  selectEUser(int start,int end);
	public boolean         deleteEUser(String eu_id);
	public boolean         updataEUser(UserInfo user,String eu_id);
}
