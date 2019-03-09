package com.duan.m.dao;

import java.util.List;

import com.duan.m.entity.EProduct;

public interface EProductDao {
	public int selectEProductCount();
	public List<EProduct> selectEProductList(int start,int end);
	public int selectMaxId();
	public boolean insertEProduct(EProduct product);
	public boolean updateEProduct(EProduct product);
	public boolean deleteEProduct(int ep_id);
	public boolean selectEProduct(EProduct product);
	public boolean updateEProduct2(int ep_id,int num);
	
	public List<EProduct> selectHotProduct();
	public int selectEProductCount2(int epc_id);
	public List<EProduct> selectEProductList2(int epc_id,int start,int end);
	public int selectEProductCount3(int epc_id2);
	public List<EProduct> selectEProductList3(int epc_id2,int start,int end);
}
