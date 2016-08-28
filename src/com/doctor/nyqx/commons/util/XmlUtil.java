package com.doctor.nyqx.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
		return xstream.toXML(textMessage);
	}
}
