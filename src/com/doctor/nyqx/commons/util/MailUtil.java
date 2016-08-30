package com.doctor.nyqx.commons.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {
	private static final String PORT = "25";
	private static final String HOST = "smtp.163.com";
	private static final String USER_NAME = "13277415918@163.com";
	private static final String PASSWORD = "amnqvjsiqaexntvb";
	private static final String FROM = "13277415918@163.com";
	private static final String NICK = "邓博士";

	/**
	 * 单条发送邮件
	 * 
	 * @param to
	 *            收件人列表，以","分割
	 * @param subject
	 *            标题
	 * @param body
	 *            内容
	 * @param filepath
	 *            附件列表,无附件传递null
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendMail(String to, String subject, String body, List<String> filepath)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		// 参数修饰
		if (body == null) {
			body = "";
		}
		if (subject == null) {
			subject = "无主题";
		}
		// 创建Properties对象
		Properties props = System.getProperties();
		// 创建信件服务器
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true"); // 通过验证
		// 得到默认的对话对象
		Session session = Session.getDefaultInstance(props, null);
		// 创建一个消息，并初始化该消息的各项元素
		MimeMessage msg = new MimeMessage(session);
		String nick = MimeUtility.encodeText(NICK);
		msg.setFrom(new InternetAddress(nick + "<" + FROM + ">"));
		// 创建收件人列表
		if (to != null && to.trim().length() > 0) {
			String[] arr = to.split(",");
			int receiverCount = arr.length;
			if (receiverCount > 0) {
				InternetAddress[] address = new InternetAddress[receiverCount];
				for (int i = 0; i < receiverCount; i++) {
					address[i] = new InternetAddress(arr[i]);
				}
				msg.addRecipients(Message.RecipientType.TO, address);
				msg.setSubject(subject);
				// 后面的BodyPart将加入到此处创建的Multipart中
				Multipart mp = new MimeMultipart();
				// 附件操作
				if (filepath != null && filepath.size() > 0) {
					for (String filename : filepath) {
						MimeBodyPart mbp = new MimeBodyPart();
						// 得到数据源
						FileDataSource fds = new FileDataSource(filename);
						// 得到附件本身并至入BodyPart
						mbp.setDataHandler(new DataHandler(fds));
						// 得到文件名同样至入BodyPart
						mbp.setFileName(fds.getName());
						mp.addBodyPart(mbp);
					}
					MimeBodyPart mbp = new MimeBodyPart();
					mbp.setText(body);
					mp.addBodyPart(mbp);
					// 移走集合中的所有元素
					filepath.clear();
					// Multipart加入到信件
					msg.setContent(mp);
				} else {
					// 设置邮件正文
					msg.setText(body);
				}
				// 设置信件头的发送日期
				msg.setSentDate(new Date());
				msg.saveChanges();
				// 发送信件
				Transport transport = session.getTransport("smtp");
				transport.connect(HOST, USER_NAME, PASSWORD);
				transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
				transport.close();
				return true;
			} else {
				System.out.println("None receiver!");
				return false;
			}
		} else {
			System.out.println("None receiver!");
			return false;
		}
	}

	public static boolean sendMails(List<String> toMails, String subject, String mailBody, List<String> filepath)
			throws AddressException, MessagingException, UnsupportedEncodingException {
		// 参数修饰
		if (mailBody == null) {
			mailBody = "";
		}
		if (subject == null) {
			subject = "无主题";
		}
		// 创建Properties对象
		Properties props = System.getProperties();
		// 创建信件服务器
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true"); // 通过验证

		// 得到默认的对话对象
		Session session = Session.getDefaultInstance(props, null);
		// 创建一个消息，并初始化该消息的各项元素
		MimeMessage msg = new MimeMessage(session);
		String nick = MimeUtility.encodeText(NICK);
		msg.setFrom(new InternetAddress(nick + "<" + FROM + ">"));

		// 创建收件人列表
		if (null != toMails && toMails.size() > 0) {
			InternetAddress[] address = new InternetAddress[toMails.size()];
			for (int i = 0; i < toMails.size(); i++) {
				address[i] = new InternetAddress(toMails.get(i).trim());
			}
			msg.addRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			// 后面的BodyPart将加入到此处创建的Multipart中
			Multipart mp = new MimeMultipart();
			// 附件操作
			if (filepath != null && filepath.size() > 0) {
				for (String filename : filepath) {
					MimeBodyPart mbp = new MimeBodyPart();
					// 得到数据源
					FileDataSource fds = new FileDataSource(filename);
					// 得到附件本身并至入BodyPart
					mbp.setDataHandler(new DataHandler(fds));
					// 得到文件名同样至入BodyPart
					mbp.setFileName(fds.getName());
					mp.addBodyPart(mbp);
				}
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setText(mailBody);
				mp.addBodyPart(mbp);
				// 移走集合中的所有元素
				filepath.clear();
				// Multipart加入到信件
				msg.setContent(mp);
			} else {
				// 设置邮件正文
				msg.setText(mailBody);
			}
			// 设置信件头的发送日期
			msg.setSentDate(new Date());
			msg.saveChanges();
			// 发送信件
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, USER_NAME, PASSWORD);
			transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
			transport.close();
			return true;
		}else{
			System.out.println("None receiver!");
			return false;
		}
	}

	public static void main(String[] args) throws AddressException, UnsupportedEncodingException, MessagingException {
		/*
		 * List<String> filepath = new ArrayList<>();
		 * 
		 * filepath.add("D:/Log4j.txt"); filepath.add("D:/服务器地址.txt");
		 * 
		 * sendMail("1412145477@qq.com", "实习考虑的怎么样了啊！",
		 * "昨天和你说的事，考虑的怎么样了,你要去哪里实习啊,我现在也要去实习了\n\n", null);
		 */
		List<String> toMails = new ArrayList<>();
		toMails.add("1412145477@qq.com");
		toMails.add("1015692443@qq.com");
		sendMails(toMails, "项目就要结束了，去干嘛啊", "昨天和你说的事，考虑的怎么样了,你要去哪里实习啊,我现在也要去实习了\n\n", null);
	}
}
