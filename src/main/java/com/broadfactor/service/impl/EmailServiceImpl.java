package com.broadfactor.service.impl;

import com.broadfactor.enums.StatusEmail;
import com.broadfactor.model.Email;
import com.broadfactor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void mailSender(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            var mimeMessage = javaMailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            messageHelper.setFrom(email.getEmailFrom());
            messageHelper.setTo(email.getEmailTo());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(email.getText());

            javaMailSender.send(mimeMessage);
            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            email.setStatusEmail(StatusEmail.ERROR);
        }
    }
}
