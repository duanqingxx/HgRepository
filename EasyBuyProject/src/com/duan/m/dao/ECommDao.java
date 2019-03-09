package com.duan.m.dao;

import java.util.List;

import com.duan.m.entity.EComment;

public interface ECommDao {
	public int            selectCommCount();
	public List<EComment> selectCommList(int start,int end);
	public int            selectMaxId();
	public boolean        insertComm(EComment comment);
	public boolean        deleteComm(int ec_id);
	public boolean        updateComm(EComment comment);
}
