package com.doctor.nyqx.entity;
/**
 * 子图文消息的实体类
 * @author Doctor邓
 *
 */
public class News {
	/**
	 * 标题
	 */
	private String Title;
	/**
	 * 描述
	 */
	private String Description;
	/**
	 * 图片Url
	 */
	private String PicUrl;
	/**
	 * 点击图片后的跳转地址
	 */
	private String Url;
	
	public News(String title, String description, String picUrl, String url) {
		super();
		Title = title;
		Description = description;
		PicUrl = picUrl;
		Url = url;
	}
	
	public News() {
		super();
	}

	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
}
