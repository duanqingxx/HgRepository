package com.duan.m.biz;

import com.duan.m.entity.EComment;
import com.duan.m.utils.Page;

public interface ECommBiz {
	
	//评论分页
	public Page<EComment> findECommPage(int currPageNo);
	//添加评论
	public boolean        addEComment(EComment comment);
	//删除评论
	public boolean        delEComment(int ec_id);
	//修改评论
	public boolean        updEComment(EComment comment);
}
