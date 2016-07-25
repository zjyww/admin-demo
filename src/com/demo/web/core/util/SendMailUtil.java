package com.demo.web.core.util;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.jfinal.log.Log;


/**
 * SendMailUtil类
 *
 * @version 1.0
 */
public class SendMailUtil {
    /**
     * 方法Logger.getLogger
     **/
    private static Log log = Log.getLog(SendMailUtil.class);

    /**
     * 方法main
     * 
     * @param args 传入参数
     */
    public static void main(String[] args) {
        try {
        	sendMail("", "", "", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws  
     * 方法sendMail
     * 
     * @param toMail 传入参数
     * @param mailSubject 传入参数
     * @param mailContent 传入参数
     * @param isHtml 传入参数
     * @throws  
     */
    public static void sendMail(String toMail, String mailSubject, String mailContent, boolean isHtml)
                        throws Exception {
    	
        JavaMailSenderImpl jmsi = new JavaMailSenderImpl();
        // 这个host相关的邮箱都会介绍
        jmsi.setHost("smtp.163.com");
        jmsi.setPort(25);
        // 或者test@sina.com，注册的邮箱的用户名和密码
        jmsi.setUsername("zjy9044@163.com");
        jmsi.setPassword("zjy110");
        Properties p = new Properties();
        // 邮件认证
        p.setProperty("mail.smtp.auth", "true");
        jmsi.setJavaMailProperties(p);
        MimeMessage mimeMsg = jmsi.createMimeMessage();
        // 编码UTF-8
        MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, "UTF-8");
        // 邮箱名必须是已注册的，后面可以添加一个展示名，不添加默认为邮箱名
        helper.setFrom("zjy9044@163.com");
        helper.setTo(toMail); 
        helper.setSubject(mailSubject);
        helper.setText(mailContent, isHtml);
        try {
			jmsi.send(mimeMsg);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}