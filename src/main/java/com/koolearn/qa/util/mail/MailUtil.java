package com.koolearn.qa.util.mail;

import com.koolearn.qa.constant.Constant;
import org.apache.commons.lang.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * @author lihuiyan
 * @description
 * @date 2016/5/27
 */
public class MailUtil {

    public static boolean sendHtmlMail(Mail mail){
        Properties pro = mail.getProperties();
        Session  sendMailSession;
        final String username =mail.getUserName();
        final String password = mail.getPassword();
        // 如果需要身份认证，根据邮件会话属性和密码验证器构造一个发送邮件的session
        if(mail.isValidate()){
            sendMailSession = Session.getInstance(pro, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        }else{
            sendMailSession = Session.getDefaultInstance(pro, new Authenticator(){});
        }
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mail.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            //创建邮件的接收者地址，并设置到邮件消息中, Message.RecipientType.TO属性表示接收者的类型为TO
            List<String> toAddress = mail.getToAddress();
            mailMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(StringUtils.join(toAddress.toArray(),Constant.COMMA)));
            //邮件的抄送者地址
            List<String> ccAddress = mail.getCcAddress();
            mailMessage.setRecipients(Message.RecipientType.CC,InternetAddress.parse(StringUtils.join(ccAddress.toArray(),Constant.COMMA)));
            // 设置邮件消息的主题
            mailMessage.setSubject(mail.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mail.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            //添加附件
            String[] attachFileNames =mail.getAttachFileNames();
            if(attachFileNames!=null){
                for(int i=0;i<attachFileNames.length;i++){
                    BodyPart bp = new MimeBodyPart();
                    FileDataSource fields = new FileDataSource(attachFileNames[i]);
                    bp.setDataHandler(new DataHandler(fields));
                    bp.setFileName(fields.getName());
                    mainPart.addBodyPart(bp);
                }
            }
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }


}
