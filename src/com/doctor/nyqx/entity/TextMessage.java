package com.doctor.nyqx.entity;
/**
 * 服务器发送给微信平台文本消息的实体类
 * @author Doctor邓
 *
 */
public class TextMessage extends BaseMessage{
	/**
	 * 文本内容
	 */
	private String Content;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
