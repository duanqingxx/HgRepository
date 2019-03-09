package com.duan.m.biz;

import java.util.List;

import com.duan.m.entity.EProduct;
import com.duan.m.utils.Page;

public interface EProductBiz {
	//商品分页
	public Page<EProduct> findEProductPage(int currPageNo);
	//查找商品
	public boolean finEProduct(EProduct product);
	//添加商品
	public boolean addEProduct(EProduct product);
	//删除商品
	public boolean delEProduct(int ep_id);
	//更新商品详情
	public boolean updEProduct(EProduct product);
	//更新商品库存
	public boolean updEProduct2(int ep_id,int num);
	
	//查找热门商品
	public List<EProduct> findHotProduct();
	//根据商品二级分类商品分页
	public Page<EProduct> findEProductPage2(int currPageNo,int epc_id);
	//根据商品一级分类商品分页
	public Page<EProduct> findEProductPage3(int currPageNo,int epc_id2);
}
