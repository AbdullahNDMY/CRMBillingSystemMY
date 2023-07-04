/**********************************************************
 * Billing System
 *
 * SUBSYSTEM NAME : C_CMN
 * SERVICE NAME   : C_CMN003
 * FUNCTION       : Set list module and sub module
 * FILE NAME      : C_CMN003Output.java
 *
 * Copyright (C) 2011 NTTDATA Corporation
 * 
**********************************************************/

package nttdm.bsys.c_cmn003.dto;

import java.io.Serializable;
import java.util.List;

import nttdm.bsys.c_cmn003.bean.C_CMN003FormBean;
import nttdm.bsys.c_cmn003.bean.SubModule;
/**
 * @author NTT Data Vietnam	
 * Action processes business 
 * @param <P>
 */
public class C_CMN003Output implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2940134444628081974L;
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
	
}
