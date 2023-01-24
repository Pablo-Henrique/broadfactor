package com.broadfactor.service.impl;

import com.broadfactor.model.Email;
import com.broadfactor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private MimeMessage mimeMessage;

    @Override
    public void mailSender(Email email) {
        try {
            messageHelper(email.getEmailFrom(), email.getEmailTo(), email.getSubject(), email.getText());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void systemMailSender(String emailTo) {
        var defaultMessage = "Parabens, cadastro realizado com sucesso!";

        try {
            messageHelper("pufim123467@gmail.com", emailTo, "", defaultMessage);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private void messageHelper(String emailFrom, String emailTo, String subject, String messageBody) throws MessagingException {
        var messageHelper = new MimeMessageHelper(mimeMessage(), true, "UTF-8");
        messageHelper.setFrom(emailFrom);
        messageHelper.setTo(emailTo);
        messageHelper.setSubject(subject);
        messageHelper.setText(messageBody);
    }

    private MimeMessage mimeMessage() {
        return mimeMessage = javaMailSender.createMimeMessage();
    }
}
