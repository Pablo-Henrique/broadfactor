package com.broadfactor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

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
    public JavaMailSender javaMailSender() {
        return javaMailSenderImpl();
    }

    private Properties propertie() {
        var propertie = new Properties();
        propertie.put("mail.smtp.auth", true);
        propertie.put("mail.smtp.timeout", 25000);
        propertie.put("mail.smtp.port", 2465);
        propertie.put("mail.smtp.socketFactory.fallback", false);
        return propertie;
    }

    private JavaMailSenderImpl javaMailSenderImpl() {
        var mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setUsername(mailUsername);
        mailSenderImpl.setPassword(mailPassword);
        mailSenderImpl.setHost(mailHost);
        mailSenderImpl.setPort(mailPort);
        mailSenderImpl.setDefaultEncoding("UTF-8");
        mailSenderImpl.setJavaMailProperties(propertie());
        return mailSenderImpl;
    }

}
