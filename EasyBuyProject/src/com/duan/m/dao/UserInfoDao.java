package com.duan.m.dao;

import com.duan.m.entity.UserInfo;

public interface UserInfoDao {
	public boolean selectUser(UserInfo user);
	
	public boolean insertUser(UserInfo user);
	
	public boolean selectUserId(String userId);
}
