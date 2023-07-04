package nttdm.bsys.m_wlm.bean;

import java.io.Serializable;

public class LevelBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3845659459353315909L;
	private String id_secgrp = null;
	private String section_no = null;
	private String level_seq = null;
	public String getId_secgrp() {
		return id_secgrp;
	}
	public void setId_secgrp(String id_secgrp) {
		this.id_secgrp = id_secgrp;
	}
	public String getSection_no() {
		return section_no;
	}
	public void setSection_no(String section_no) {
		this.section_no = section_no;
	}
	public void setLevel_seq(String level_seq) {
		this.level_seq = level_seq;
	}
	public String getLevel_seq() {
		return level_seq;
	}
}
