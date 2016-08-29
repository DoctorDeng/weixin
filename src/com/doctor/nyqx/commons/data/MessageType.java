package com.doctor.nyqx.commons.data;
/**
 * 存储微信发送过来的消息的所有消息类型
 * @author Doctor邓
 *
 */
public interface MessageType {
	static final String MESSAGE_TEXT = "text";
	static final String MESSAGE_NEWS = "news";
	static final String MESSAGE_IMAGE = "image";
	static final String MESSAGE_VOICE = "voice";
	static final String MESSAGE_VIDEO = "video";
	static final String MESSAGE_LINK = "link";
	static final String MESSAGE_LOCATION = "location";
	static final String MESSAGE_EVENT = "event";
	static final String MESSAGE_SUBSCRIBE = "subscribe";
	static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	static final String MESSAGE_CLICK = "CLICK";
	static final String MESSAGE_VIEW = "VIEW";
}
