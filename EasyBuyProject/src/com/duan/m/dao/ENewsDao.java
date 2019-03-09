package com.duan.m.dao;

import java.util.List;

import com.duan.m.entity.ENews;

public interface ENewsDao {
	public List<ENews> selectHotNews();
	public ENews       selectENewsDetail(int en_id);
	public int         selectENewsCount();
	public List<ENews> selectENews(int start,int end);
	public int         selectMaxId();
	public boolean    insertENews(ENews news);
	public boolean    deleteENews(int en_id);
	public boolean    updataENews(ENews news);
	
}
