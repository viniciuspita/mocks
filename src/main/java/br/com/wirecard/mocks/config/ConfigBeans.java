package br.com.wirecard.mocks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBeans {

    @Scope("prototype")
    @Bean
    public RestTemplate criaResTemplate(){
        return new RestTemplate();
    }
}