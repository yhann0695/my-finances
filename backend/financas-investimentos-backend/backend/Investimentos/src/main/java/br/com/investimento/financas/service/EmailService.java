package br.com.investimento.financas.service;

import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public void enviarEmail(String remetente, String destinatario, String assunto, String template,
                         Map<String, Object> variaveis)  {

        Context context = new Context(new Locale("pt", "BR"));
        variaveis.entrySet().stream().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));

        String mensagem = templateEngine.process(template, context);

        this.enviar(remetente, destinatario, assunto, mensagem);
                            
    }

    public void enviar(String remetente, String destinatario, String assunto, String mensagem) {
        
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

            mimeMessageHelper.setFrom(remetente);
            mimeMessageHelper.setTo(destinatario);
            mimeMessageHelper.setSubject(assunto);
            mimeMessageHelper.setText(mensagem, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Problemas com o envio de e-mail: ", e);
        }
    }
    
}