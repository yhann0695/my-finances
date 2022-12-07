package br.com.investimento.financas.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Autowired
    private InvestimentosConfig props;
    
    @Bean
    public JavaMailSender javaMailSender(){
        Properties properties = new Properties();
        properties.put("mail.smtp.protocol", "smtp");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.connectiontimeout", 10000);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(properties);
        mailSender.setHost(props.getMail().getHost());
        mailSender.setPort(props.getMail().getPort());
        mailSender.setUsername(props.getMail().getUsername());
        mailSender.setPassword(props.getMail().getPassword());
        return mailSender;
    }
}