/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : C_CMN
 * SERVICE NAME   : C_CMN003
 * FUNCTION       : Define bean
 * FILE NAME      : C_CMN003FormBean.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/



package nttdm.bsys.c_cmn003.bean;

import java.util.List;
/**
 * @author NTT Data Vietnam	
 * Action processes business from C_CMN003 form
 * @param <P>
 */
public class C_CMN003FormBean {
	private static final long serialVersionUID = -5538432094237818425L;
	private String id_module;
	private String mod_name;
	private String mod_desc;
	private String date_created;
	private String date_updated;
	private String id_login;
	private String order_seq;
	private String is_display;
	/*
	 * Sub Module array
	 * */
	private List<SubModule> arr_subModule = null;
	/*
	 * Module array
	 * */
	private List<C_CMN003FormBean> arr_Module = null;
	public List<SubModule> getArr_subModule() {
		return arr_subModule;
	}
	public void setArr_subModule(List<SubModule> arr_subModule) {
		this.arr_subModule = arr_subModule;
	}
	public List<C_CMN003FormBean> getArr_Module() {
		return arr_Module;
	}
	public void setArr_Module(List<C_CMN003FormBean> arr_Module) {
		this.arr_Module = arr_Module;
	}
	public String getId_module() {
		return id_module;
	}
	public void setId_module(String id_module) {
		this.id_module = id_module;
	}
	public String getMod_name() {
		return mod_name;
	}
	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}
	public String getMod_desc() {
		return mod_desc;
	}
	public void setMod_desc(String mod_desc) {
		this.mod_desc = mod_desc;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_updated() {
		return date_updated;
	}
	public void setDate_updated(String date_updated) {
		this.date_updated = date_updated;
	}
	public String getId_login() {
		return id_login;
	}
	public void setId_login(String id_login) {
		this.id_login = id_login;
	}
	public String getOrder_seq() {
		return order_seq;
	}
	public void setOrder_seq(String order_seq) {
		this.order_seq = order_seq;
	}
	public String getIs_display() {
		return is_display;
	}
	public void setIs_display(String is_display) {
		this.is_display = is_display;
	} 
	
}
