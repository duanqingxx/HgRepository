package com.duan.m.biz;

import java.util.List;

import com.duan.m.entity.ENews;
import com.duan.m.utils.Page;

public interface ENewsBiz {
	//查找首页热点新闻
	public List<ENews> findHotNews();
	//查找新闻详情
	public ENews       findENewsDetail(int en_id);
	//新闻分页
	public Page<ENews> findENewsPage(int currPageNo);
	//添加新闻
	public boolean     addENews(ENews news);
	//修改新闻
	public boolean     updENews(ENews news);
	//删除新闻
	public boolean     delENews(int en_id);
}
