package com.broadfactor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.util.Properties;

@Configuration
public class JavaMailSenderConfig {

    @Bean
    public JavaMailSender javaMailSender() throws MessagingException {
        var mailSender = new JavaMailSenderImpl();

        mailSender.setUsername("AKIASZR4EXR7OUEONRMY");
        mailSender.setPassword("BOjITRaODBgKUtYOf1V5rKbECMV1B18JZltgF3y6wooq");
        mailSender.setHost("email-smtp.us-east-1.amazonaws.com");
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
