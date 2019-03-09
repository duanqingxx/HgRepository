package com.duan.m.biz;

import com.duan.m.entity.UserInfo;
import com.duan.m.utils.Page;

public interface EUserBiz {
	//用户信息分页
	public Page<UserInfo> findEUserPage(int currPageNo);
	//更新用户信息
	public boolean        updEUser(UserInfo user,String eu_id);
	//删除用户信息
	public boolean        delEUser(String eu_id);
}
