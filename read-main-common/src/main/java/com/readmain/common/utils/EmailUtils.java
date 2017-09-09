//package com.readmain.common.utils;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//
//@Component
//public class EmailUtils {
//
//    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);
//
//    @Resource
//    private JavaMailSender mailSender;
//
//    @Resource
//    private SimpleMailMessage simpleMailMessage;
//
//    /**
//     * sendMail
//     *
//     * @param subject 邮件主题
//     * @param content 邮件主题内容
//     * @param to      收件人Email地址
//     */
//    public void sendMail(String subject, String content, String to) throws Exception {
//        try {
//            simpleMailMessage.setSubject(subject);
//            simpleMailMessage.setTo(to);
//            simpleMailMessage.setText(content);
//            mailSender.send(simpleMailMessage);
//        } catch (Exception e) {
//            logger.error("邮件发送失败{}", e);
//            throw new Exception("邮件发送失败");
//        }
//    }
//
//    public void sendMimeMail(String subject, String content, String[] tos, String[] ccs, File... files) throws Exception {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
//            messageHelper.setFrom(simpleMailMessage.getFrom()); //设置发件人Email
//            messageHelper.setSubject(subject); //设置邮件主题
//            messageHelper.setText(content);   //设置邮件主题内容
//            messageHelper.setTo(tos);
//            if (ArrayUtils.isNotEmpty(ccs)) {
//                messageHelper.setBcc(ccs);
//            }
//            for (File file : files) {
//                messageHelper.addAttachment(file.getName(), file);
//            }
//            mailSender.send(mimeMessage);
//        } catch (Exception e) {
//            logger.error("邮件发送失败{}", e);
//            throw new Exception("邮件发送失败");
//        }
//    }
//
//}
