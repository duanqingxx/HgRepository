package com.duan.m.biz;

import com.duan.m.entity.UserInfo;

public interface UserInfoBiz {
	//查找用户
	public boolean     findUser(UserInfo user);
	//添加用户
	public boolean      addUser(UserInfo user);
	//根据id查找用户
	public boolean findUserById(String userId);
}
