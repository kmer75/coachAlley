package com.example.Configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@PropertySource("classpath:email.properties")
@Configuration
public class EmailConfig {

    @Value("${email.host}")
    private String host;
    @Value("${email.port}")
    private int port;
    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;
    @Value("${mail.smtp.auth}")
    private boolean auth;
    @Value("${mail.transport.protocol}")
    private String protocol;
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    @Value("${mail.debug}")
    private boolean debug;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.transport.protocol", protocol);
        properties.put("mail.smtp.starttls.enable", starttls);
        properties.put("mail.debug", debug);
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }
}
