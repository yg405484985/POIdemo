package com.hzit.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static boolean sendMailMessage(String receMail, String receName, String code) {

		try {
			// 1.设置配置文件
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
			// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为:
			// smtp.xxx.com
			// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
			props.setProperty("mail.smtp.host", "smtp.163.com"); // 发件人的邮箱的 SMTP
																	// 服务器地址
			props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

			// 2.创建会话对象
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);

			// 3.创建邮件(主题，内容，收件人)
			Message message = createaMessage(session, "hzit_test@163.com", receMail, receName, code);

			// 4.获取传输对象(发送)
			Transport transport = session.getTransport();
			// 验证账号信息
			transport.connect("hzit_test@163.com", "aa12345678");
			transport.sendMessage(message, message.getAllRecipients());

			// 5.关闭
			transport.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 创建邮件对象
	 * 
	 * @param session
	 * @param sendMail
	 * @param receMail
	 * @return
	 */
	public static MimeMessage createaMessage(Session session, String sendMail, String receMail, String receName,
			String code) {

		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			// 设置发件人
			mimeMessage.setFrom(new InternetAddress(sendMail, "007号客服", "UTF-8"));

			// 设置主题
			mimeMessage.setSubject("用户激活中心", "utf-8");

			// 设置内容
			mimeMessage.setContent("<h1>" + receName
					+ ":欢迎来到吃了睡用户中心,使用前请先激活 连接:<a href='http://localhost:8080/activeAccount?accountCode=" + code
					+ "'>请点击激活</a></h1>", "text/html;charset=UTF-8");

			// 3. To: 收件人（可以增加多个收件人、抄送、密送）
			// TO - 收件人；CC - 抄送人；BCC - 暗送人。
			// 163邮箱 发送出去的邮件会经常被当做垃圾邮件，所以发送之前给自己抽送一份
			mimeMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(sendMail, "抄送", "UTF-8"));
			mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receMail, receName, "UTF-8"));

			// 4.日期
			mimeMessage.setSentDate(new Date());

			mimeMessage.saveChanges();

			return mimeMessage;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
