package com.duan.m.dao;

import java.util.List;

import com.duan.m.entity.EProduct_Category;

public interface EProCateDao {
	public int                     selectEProCateCount();
	public List<EProduct_Category> selectEProCateList(int start,int end);
	public List<EProduct_Category> selectEProCateFather();
	public boolean                 updateEProCate(EProduct_Category category);
	public boolean                 deleteEProCate(int epc_id);
	public int                     selectMaxId();
	public boolean                 insertEProCate(EProduct_Category category);
	
	
	public List<EProduct_Category> selectAllEProCate();
	public EProduct_Category       selectEProCate(int epc_id);
}
