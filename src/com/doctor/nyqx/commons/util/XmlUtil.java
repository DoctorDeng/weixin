package com.doctor.nyqx.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.doctor.nyqx.commons.data.MessageType;
import com.doctor.nyqx.commons.data.ResourceDir;
import com.doctor.nyqx.entity.News;
import com.doctor.nyqx.entity.NewsMessage;
import com.doctor.nyqx.entity.TextMessage;
import com.thoughtworks.xstream.XStream;

public class XmlUtil {
	/**
	 * XML文档转换为Map
	 * @param request
	 * @return  包含XML文档信息的Map集合
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String,String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		
		InputStream inputStream = request.getInputStream();
		/**
		 * 获得文档对象
		 */
		Document doc = reader.read(inputStream);
		/**
		 * 获得根元素
		 */
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		} 
		
		inputStream.close();
		
		return map;
	}
	/**
	 * 将消息对象转换为XML
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	/**
	 * 菜单提示信息文本
	 * @return
	 */
	public static String menuText() {
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您的关注,请按照菜单提示进行操作:\n\n");
		
		sb.append("回复【1】:查看我的名称\n\n");
		sb.append("回复【2】:查看我的性别\n\n");
		sb.append("回复【?】:查看菜单\n\n");
		return sb.toString();
	}
	/**
	 * 初始化文本消息
	 * @param toUserName  开发者微信号
	 * @param fromUserName 接收方账号(收到的OpenID)
	 * @param content 文本消息内容
	 * @return String
	 */
	public static String initText(String toUserName,String fromUserName,String content) {
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageType.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime()+"");
		text.setContent(content);
		return textMessageToXml(text);
	}
	/**
	 * 第一个菜单
	 */
	public static String firstMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("哈哈哈不告诉你,你猜啊!!");
		
		return sb.toString();
	}
	/**
	 * 第一个菜单
	 */
	public static String secondMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("男~~~~很失望吧!!");
		
		return sb.toString();
	}
	/**
	 * 图文消息转为xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}
	/**
	 * 图文消息的组装
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName) {
		String message = null;
		List<News> newsList = new ArrayList<>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("测试图文消息一");
		news.setDescription("这是测试消息的描述");
		System.out.println(ResourceDir.IMAGE_URL+"/1.jpg");
		news.setPicUrl(ResourceDir.IMAGE_URL+"/1.jpg");
		news.setUrl("http://www.imooc.com");
		
		newsList.add(news);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime()+"");
		newsMessage.setMsgType(MessageType.MESSAGE_NEWS);
		newsMessage.setArticleCount(newsList.size());
		newsMessage.setArticles(newsList);
		
		message = newsMessageToXml(newsMessage);
		
		return message;
	}
}
