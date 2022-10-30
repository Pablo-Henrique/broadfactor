package com.broadfactor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:placeholder-smtp.properties")
public class JavaMailSenderConfig {

    @Value("${mail-host}")
    private String mailHost;

    @Value("${mail-username}")
    private String mailUsername;

    @Value("${mail-password}")
    private String mailPassword;

    @Value("${mail-port}")
    private Integer mailPort;

    @Bean
    public JavaMailSender javaMailSender() throws MessagingException {
        var mailSender = new JavaMailSenderImpl();

        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setDefaultEncoding("UTF-8");

        Properties pros = new Properties();
        pros.put("mail.smtp.auth", true);
        pros.put("mail.smtp.timeout", 25000);
        pros.put("mail.smtp.port", 2465);
        pros.put("mail.smtp.socketFactory.fallback", false);
        mailSender.setJavaMailProperties(pros);
        return mailSender;
    }
}
