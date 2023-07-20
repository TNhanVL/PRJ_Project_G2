/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Services;

import javax.mail.Authenticator;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author TTNhan
 */
/**
 *
 * @author Thanh Duong
 */
public class Email {
//    thanhduongjnguyen@gmail.com
//    cpqrdilisnasjxoe

    final static String from = "yojihangroup@gmail.com";
    final static String password = "drmoubkcmogfmrlu";

    public static int mailTo(String obj, String title, String type, String content) {
        final String to = obj;

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

//        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP HOST
//        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

//             Create Authentcator
        Authenticator auth = new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

        };

        // Phien lam viec
        Session session = Session.getInstance(props, auth);

        MimeMessage msg = new MimeMessage(session);

        try {
            //Type 
            msg.addHeader("Content-type", "text;charset=UTF-8");
            //From
            msg.setFrom(from);
            //To
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parseHeader(to, false));
            //Title
            msg.setSubject(title, "UTF-8");
            //Time
            msg.setSentDate(new Date());

            //Set reply
            //msg.setReplyto(InternetAddress.parseHeader(from, false))
            msg.setText(content, "UTF-8", type);

            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public static void main(String[] args) {
        mailTo("thanhduongjnguyen@gmail.com", "You have ...", "html", "<a href=\"https://www.facebook.com\">View </a>)");
    }

}
