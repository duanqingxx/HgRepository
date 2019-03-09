package com.duan.m.biz;

import java.util.List;

import com.duan.m.entity.EProduct_Category;
import com.duan.m.utils.Page;

public interface EProCateBiz {
	//商品类分页
	public Page<EProduct_Category> findProCatePage(int currPageNo);
	//查找商品一级分类
	public List<EProduct_Category> findProCateOne();
	//更新商品分类
	public boolean                 updProCate(EProduct_Category category);
	//删除商品分类
	public boolean                 delProCate(int epc_id);
	//添加商品分类
	public boolean                 addProCate(EProduct_Category category);
	//查找全部商品分类
	public List<EProduct_Category> findAllProCate();
	//根据id查找分类对象
	public EProduct_Category       findProCate(int epc_id);
}
