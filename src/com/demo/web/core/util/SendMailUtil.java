package com.demo.web.core.util;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法sendMail
     * 
     * @param toMail 传入参数
     * @param mailSubject 传入参数
     * @param mailContent 传入参数
     * @param isHtml 传入参数
     
    public static void sendMail(String toMail, String mailSubject, String mailContent, boolean isHtml)
                        throws MessagingException {

        JavaMailSenderImpl jmsi = new JavaMailSenderImpl();
        // 这个host相关的邮箱都会介绍
        jmsi.setHost("smtp.qq.com");
        jmsi.setPort(25);
        // 或者test@sina.com，注册的邮箱的用户名和密码
        jmsi.setUsername("429257500");
        jmsi.setPassword("wscce1230$");
        Properties p = new Properties();
        // 邮件认证
        p.setProperty("mail.smtp.auth", "true");
        jmsi.setJavaMailProperties(p);
        MimeMessage mimeMessage = jmsi.createMimeMessage();
        // 编码UTF-8
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        // 邮箱名必须是已注册的，后面可以添加一个展示名，不添加默认为邮箱名
        helper.setFrom("429257500@qq.com");
        helper.setTo(toMail);// "zzb@.net"
        helper.setSubject(mailSubject);
        helper.setText(mailContent, isHtml);
        jmsi.send(mimeMessage);
    }
*/
}