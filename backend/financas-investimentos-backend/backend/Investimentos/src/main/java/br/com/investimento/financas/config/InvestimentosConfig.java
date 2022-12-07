package br.com.investimento.financas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("investimentos")
@Getter
public class InvestimentosConfig {

    
    private final Mail mail = new Mail();
    @Getter
    @Setter
    public static class Mail {
        private String host;
        private Integer port;
        private String username;
        private String password;
    }
}