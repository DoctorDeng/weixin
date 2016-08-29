package com.doctor.nyqx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.nyqx.commons.data.MessageType;
import com.doctor.nyqx.commons.util.XmlUtil;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@RequestMapping("/messageJudge")
	public String messageJudge(HttpServletResponse response,HttpServletRequest request) {
		Map<String,String> map = null;
		try {
			map = XmlUtil.xmlToMap(request);
			request.setAttribute("messageMap", map );
		}  catch (IOException e) {
			System.out.println("=====没有收到回复信息=======");
			e.printStackTrace();
		} catch (DocumentException e) {
			System.out.println("=====Xml文本消息转换异常=======");
			e.printStackTrace();
		} 
		
		if (map != null) {
			switch(map.get("MsgType")) {
			case MessageType.MESSAGE_TEXT:
				return "forward:/message/textMessage" ;
			case MessageType.MESSAGE_VIDEO:
				return "forward:/message/videoMessage" ;
			case MessageType.MESSAGE_IMAGE:
				return "forward:/message/imageMessage" ;
			case MessageType.MESSAGE_LINK:
				return "forward:/message/linkMessage" ;
			case MessageType.MESSAGE_VOICE:
				return "forward:/message/voicdMessage" ;
			case MessageType.MESSAGE_EVENT:
				return "forward:/message/eventMessage" ;
			/*case MessageType.MESSAGE_CLICK:
				return "forward:/message/clickMessage" ;
			case MessageType.MESSAGE_SUBSCRIBE:
				return "forward:/message/subscribeMessage" ;
			case MessageType.MESSAGE_UNSUBSCRIBE:
				return "forward:/message/unsubscribeMessage" ;*/
			}
			return "forward:/message/errorMessage";
		} else {
			return "forward:/message/errorMessage";
		}
	}
	
	@RequestMapping("/textMessage")
	public void textMessage(HttpServletRequest request, HttpServletResponse response) {
		String message = "对不起,服务器出错了";
		PrintWriter out = null;
		try {
			out = response.getWriter();
			@SuppressWarnings("unchecked")
			Map<String,String> map = (Map<String,String>)request.getAttribute("messageMap");
			
			String toUserName   = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			/*String createTime   = map.get("CreateTime");*/
			String msgType      = map.get("MsgType");
			String content      = map.get("Content");
			
			if(MessageType.MESSAGE_TEXT.equals(msgType)) {
				
				switch (content) {
				case "1":
					message = XmlUtil.initText(toUserName, fromUserName, XmlUtil.firstMenu());
					break;
				case "2":
					message = XmlUtil.initText(toUserName, fromUserName, XmlUtil.secondMenu());
					break;
				case "4":
					message = XmlUtil.initNewsMessage(toUserName, fromUserName);
					break;
				case "?":
					message = XmlUtil.initText(toUserName, fromUserName, XmlUtil.menuText());
					break;
				case "？":
					message = XmlUtil.initText(toUserName, fromUserName, XmlUtil.menuText());
					break;
				default:
					message = XmlUtil.initText(toUserName, fromUserName, "哎呀~(づ￣3￣)づ╭❤～\n\n"
							+"亲您输入找不到内容哦");
					break;
				}
			}
			out.print(message);
		} catch (IOException e) {
			System.out.println("======没有收到回复文本信息=======");
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	@RequestMapping("/errorMessage")
	public void errorMessage(HttpServletRequest request, HttpServletResponse response){
		
	}
	@RequestMapping("/eventMessage")
	public void eventMessage(HttpServletRequest request, HttpServletResponse response) {
		String message = "没有菜单";
		PrintWriter out = null;
		try {
			out = response.getWriter();
			@SuppressWarnings("unchecked")
			Map<String,String> map = (Map<String,String>)request.getAttribute("messageMap");
			String eventType = map.get("Event");
			if (MessageType.MESSAGE_SUBSCRIBE.equals(eventType)) {
				message = XmlUtil.initText(map.get("ToUserName"), map.get("FromUserName"), XmlUtil.menuText());
			}
			out.print(message);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
