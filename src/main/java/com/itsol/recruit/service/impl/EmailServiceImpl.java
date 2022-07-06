package com.itsol.recruit.service.impl;

import com.itsol.recruit.service.mapper.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setSubject("Confirm OTP");
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new IllegalStateException("failed to send email");
        }
    }

    public String buildOtpEmail(String name, String otp) {
        return "<p>Xin chào " + name + ".Nhập mã OTP như dưới đây dể đổi mật khẩu </p>"
                + "<br>" + "<h3>" + otp + "</h3>" + "<br>"
                + "<p>Mã OTP này sẽ hết hạn trong 5 phút</p>";

    }
}




