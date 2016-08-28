package com.doctor.nyqx.entity;
/**
 * 服务器发送给微信平台文本消息的实体类
 * @author Doctor邓
 *
 */
public class TextMessage {
	/**
	 * 接收方账号(收到的OpenID)
	 */
	private String ToUserName;
	/**
	 * 开发者微信号
	 */
	private String FromUserName;
	/**
	 * 消息创建时间(整型)
	 */
	private String CreateTime;
	/**
	 * image
	 */
	private String MsgType;
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id
	 */
	private String Content;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
}
