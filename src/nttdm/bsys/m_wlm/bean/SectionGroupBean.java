package nttdm.bsys.m_wlm.bean;

import java.io.Serializable;

public class SectionGroupBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2435756222581771040L;
	private String id_secgrp = null;
	private String secgrp_desc = null;
	private Integer order_seq = null;
	
	public String getId_secgrp() {
		return id_secgrp;
	}
	public void setId_secgrp(String id_secgrp) {
		this.id_secgrp = id_secgrp;
	}
	public String getSecgrp_desc() {
		return secgrp_desc;
	}
	public void setSecgrp_desc(String secgrp_desc) {
		this.secgrp_desc = secgrp_desc;
	}
	public Integer getOrder_seq() {
		return order_seq;
	}
	public void setOrder_seq(Integer order_seq) {
		this.order_seq = order_seq;
	}
}
