package com.duan.m.entity;

public class EProduct {
	private int     ep_id;
	private String  ep_name;
	private String  ep_description;
	private float   ep_price;
	private int     ep_stock;
	private int     epc_id;
	private int     epc_id2;
	private String  ep_file_name;
	
	public int getEp_id() {
		return ep_id;
	}
	public void setEp_id(int ep_id) {
		this.ep_id = ep_id;
	}
	public String getEp_name() {
		return ep_name;
	}
	public void setEp_name(String ep_name) {
		this.ep_name = ep_name;
	}
	public String getEp_description() {
		return ep_description;
	}
	public void setEp_description(String ep_description) {
		this.ep_description = ep_description;
	}
	public float getEp_price() {
		return ep_price;
	}
	public void setEp_price(float ep_price) {
		this.ep_price = ep_price;
	}
	public int getEp_stock() {
		return ep_stock;
	}
	public void setEp_stock(int ep_stock) {
		this.ep_stock = ep_stock;
	}
	public int getEpc_id() {
		return epc_id;
	}
	public void setEpc_id(int epc_id) {
		this.epc_id = epc_id;
	}
	public int getEpc_id2() {
		return epc_id2;
	}
	public void setEpc_id2(int epc_id2) {
		this.epc_id2 = epc_id2;
	}
	public String getEp_file_name() {
		return ep_file_name;
	}
	public void setEp_file_name(String ep_file_name) {
		this.ep_file_name = ep_file_name;
	}
	
}
