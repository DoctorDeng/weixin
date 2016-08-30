package com.doctor.nyqx.entity;
/**
 * 用户邮件实体
 * @author Doctor邓
 *
 */
public class Email {
	/**
	 * 邮件ID
	 */
	private int uid;
	/**
	 * 邮件地址
	 */
	private String uemail;
	
	public String getUemail() {
		return uemail;
	}
	
	public void setUemail(String email) {
		this.uemail = email;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Email(int uid, String uemail) {
		super();
		this.uid = uid;
		this.uemail = uemail;
	}

	public Email() {
		super();
	}
}
