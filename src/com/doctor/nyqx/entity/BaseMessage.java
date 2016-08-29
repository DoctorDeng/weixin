package com.doctor.nyqx.entity;
/**
 * 所有微信消息的基类
 * @author Doctor邓
 *
 */
public class BaseMessage {
	/**
	 * 接收方账号
	 */
	private String ToUserName;
	/**
	 * 发消息方微信号
	 */
	private String FromUserName;
	/**
	 * 消息创建时间(整型)
	 */
	private String CreateTime;
	/**
	 * 消息类型
	 */
	private String MsgType;
	
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
}
