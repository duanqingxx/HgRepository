package com.duan.m.entity;

import java.sql.Timestamp;

public class UserInfo {
	private String eu_user_id;
	private String eu_user_name;
	private String eu_passWord;
	private String eu_sex;
	private Timestamp eu_birthday;
	private String eu_indentity_code;
	private String eu_email;
	private String eu_mobile;
	private String eu_address;
	private float   eu_login;
	private int    eu_status;
	
	public String getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(String eu_user_id) {
		this.eu_user_id = eu_user_id;
	}
	public String getEu_user_name() {
		return eu_user_name;
	}
	public void setEu_user_name(String eu_user_name) {
		this.eu_user_name = eu_user_name;
	}
	public String getEu_passWord() {
		return eu_passWord;
	}
	public void setEu_passWord(String eu_passWord) {
		this.eu_passWord = eu_passWord;
	}
	public String getEu_sex() {
		return eu_sex;
	}
	public void setEu_sex(String eu_sex) {
		this.eu_sex = eu_sex;
	}
	public Timestamp getEu_birthday() {
		return eu_birthday;
	}
	public void setEu_birthday(Timestamp eu_birthday) {
		this.eu_birthday = eu_birthday;
	}
	public String getEu_indentity_code() {
		return eu_indentity_code;
	}
	public void setEu_indentity_code(String eu_indentity_code) {
		this.eu_indentity_code = eu_indentity_code;
	}
	public String getEu_email() {
		return eu_email;
	}
	public void setEu_email(String eu_email) {
		this.eu_email = eu_email;
	}
	public String getEu_mobile() {
		return eu_mobile;
	}
	public void setEu_mobile(String eu_mobile) {
		this.eu_mobile = eu_mobile;
	}
	public String getEu_address() {
		return eu_address;
	}
	public void setEu_address(String eu_address) {
		this.eu_address = eu_address;
	}
	public float getEu_login() {
		return eu_login;
	}
	public void setEu_login(float eu_login) {
		this.eu_login = eu_login;
	}
	public int getEu_status() {
		return eu_status;
	}
	public void setEu_status(int eu_status) {
		this.eu_status = eu_status;
	}
	
}
