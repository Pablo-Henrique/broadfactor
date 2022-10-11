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
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void mailSender(Email email) throws MessagingException {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(email.getEmailFrom());
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getText());

            javaMailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            e.printStackTrace();
            email.setStatusEmail(StatusEmail.ERROR);
        }
    }
}
