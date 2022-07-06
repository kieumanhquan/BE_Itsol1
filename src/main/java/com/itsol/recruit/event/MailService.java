package com.itsol.recruit.event;

import com.itsol.recruit.entity.User;
import com.itsol.recruit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailService implements IMailService{

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendRegistrationUserConfirm(String email) {
        User user = userService.findUserByEmail(email);

        String confirmationUrl = "http://localhost:9090/api/public/active_account/" + user.getId();

        String subject = "Account registration confirmation.";
        String content = "Please click on the link below to activate account \n"
                + confirmationUrl;

        sendEmail(email, subject, content);
    }

    private void sendEmail(final String recipientEmail, final String subject, final String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(content);

        javaMailSender.send(message);
    }
}
