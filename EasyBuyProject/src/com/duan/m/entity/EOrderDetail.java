package com.duan.m.entity;

public class EOrderDetail {
	private int   eod_id;
	private int   eo_id;
	private int   ep_id;
	private int   eod_quantity;
	private float eod_cost;
	private EProduct product;
	
	public int getEod_id() {
		return eod_id;
	}
	public void setEod_id(int eod_id) {
		this.eod_id = eod_id;
	}
	public int getEo_id() {
		return eo_id;
	}
	public void setEo_id(int eo_id) {
		this.eo_id = eo_id;
	}
	public int getEp_id() {
		return ep_id;
	}
	public void setEp_id(int ep_id) {
		this.ep_id = ep_id;
	}
	public int getEod_quantity() {
		return eod_quantity;
	}
	public void setEod_quantity(int eod_quantity) {
		this.eod_quantity = eod_quantity;
	}
	public float getEod_cost() {
		return eod_cost;
	}
	public void setEod_cost(float eod_cost) {
		this.eod_cost = eod_cost;
	}
	
	public EProduct getProduct() {
		return product;
	}
	public void setProduct(EProduct product) {
		this.product = product;
	}
}
