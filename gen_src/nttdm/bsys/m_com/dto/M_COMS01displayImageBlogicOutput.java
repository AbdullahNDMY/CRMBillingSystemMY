/*
 * @(#)M_COMS01displayImageBlogicOutput.java
 *
 *
 */
package nttdm.bsys.m_com.dto;

import java.io.Serializable;

/**
 * OutputDTO class.
 * 
 * @author helloworld
 */
public class M_COMS01displayImageBlogicOutput implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5776993753553919631L;

	/**
     * 
     */
	private byte[] photo;

	/**
	 * photo を取得する
	 * 
	 * @return photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * photo を設定する
	 * 
	 * @param photo
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

}