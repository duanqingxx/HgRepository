package com.duan.m.entity;

import java.sql.Timestamp;
import java.util.List;

public class EOrder {
	private int       eo_id;
	private String    eo_user_id;
	private String    eo_user_name;
	private String    eo_user_address;
	private Timestamp eo_create_time;
	private float     eo_cost;
	private int       eo_status;
	private List<EOrderDetail> eodList;
	
	public int getEo_id() {
		return eo_id;
	}
	public void setEo_id(int eo_id) {
		this.eo_id = eo_id;
	}
	public String getEo_user_id() {
		return eo_user_id;
	}
	public void setEo_user_id(String eo_user_id) {
		this.eo_user_id = eo_user_id;
	}
	public String getEo_user_name() {
		return eo_user_name;
	}
	public void setEo_user_name(String eo_user_name) {
		this.eo_user_name = eo_user_name;
	}
	public String getEo_user_address() {
		return eo_user_address;
	}
	public void setEo_user_address(String eo_user_address) {
		this.eo_user_address = eo_user_address;
	}
	public Timestamp getEo_create_time() {
		return eo_create_time;
	}
	public void setEo_create_time(Timestamp eo_create_time) {
		this.eo_create_time = eo_create_time;
	}
	public float getEo_cost() {
		return eo_cost;
	}
	public void setEo_cost(float eo_cost) {
		this.eo_cost = eo_cost;
	}
	public int getEo_status() {
		return eo_status;
	}
	public void setEo_status(int eo_status) {
		this.eo_status = eo_status;
	}
	public List<EOrderDetail> getEodList() {
		return eodList;
	}
	public void setEodList(List<EOrderDetail> eodList) {
		this.eodList = eodList;
	}
	
}
