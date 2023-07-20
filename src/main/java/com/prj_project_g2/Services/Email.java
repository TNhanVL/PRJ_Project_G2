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

    
    
    public static void main(String[] args) {
        final String from = "yojihangroup@gmail.com";
        final String password = "drmoubkcmogfmrlu";

        final String to = "nhan12341184@gmail.com";

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
            msg.setSubject("Chúc mừng tạo tài khoản thành công!");
            //Time
            msg.setSentDate(new Date());

            //Set reply
            //msg.setReplyto(InternetAddress.parseHeader(from, false))
            msg.setText("Chào bạn,\n"
                    + "\n"
                    + "Tôi xin gửi lời chúc mừng đặc biệt! Tài khoản Yojihan của bạn đã được đăng ký thành công. Bạn đã trở thành thành viên của tổ chức bán khóa học trực tuyến hàng đầu - Yojihan.\n"
                    + "\n"
                    + "Từ giờ, bạn đã có quyền truy cập vào tất cả các khóa học trực tuyến đa dạng và chất lượng cao mà Yojihan cung cấp. Bạn có thể tiếp cận kiến thức và kỹ năng mới trong lĩnh vực mà bạn quan tâm. Chúng tôi cam kết mang đến cho bạn trải nghiệm học tập tuyệt vời và hỗ trợ chuyên nghiệp trong suốt quá trình học.\n"
                    + "\n"
                    + "Để bắt đầu, hãy đăng nhập vào tài khoản của bạn trên trang web Yojihan. Tại đây, bạn sẽ tìm thấy danh sách các khóa học, tài liệu, bài giảng và các tài nguyên học tập khác. Hãy khám phá và tận hưởng những kiến thức mới mà chúng tôi cung cấp.\n"
                    + "\n"
                    + "Nếu bạn gặp bất kỳ vấn đề hoặc có câu hỏi nào liên quan đến tài khoản hoặc các khóa học của bạn, xin vui lòng liên hệ với chúng tôi. Đội ngũ hỗ trợ khách hàng của Yojihan sẽ sẵn sàng hỗ trợ bạn.\n"
                    + "\n"
                    + "Một lần nữa, chúc mừng bạn đã gia nhập cộng đồng học viên của Yojihan. Hy vọng bạn sẽ có một hành trình học tập thú vị và thành công cùng chúng tôi!\n"
                    + "\n"
                    + "Trân trọng,\n"
                    + "Yojihan\n"
                    + "Bộ phận Hỗ trợ Khách hàng Yojihan", "UTF-8");

            Transport.send(msg);

        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
