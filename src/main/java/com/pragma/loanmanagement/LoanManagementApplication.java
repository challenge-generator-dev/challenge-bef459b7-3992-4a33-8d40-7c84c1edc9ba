package com.pragma.loanmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@EnableScheduling
public class LoanManagementApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LoanManagementApplication.class, args);
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        configureLogging();
    }
    
    private void configureLogging() {
        LoggingSystem.get(LoggingSystem.class.getClassLoader())
            .setLogLevel("org.hibernate.SQL", LogLevel.DEBUG);
        LoggingSystem.get(LoggingSystem.class.getClassLoader())
            .setLogLevel("com.pragma.loanmanagement", LogLevel.INFO);
    }
    
    @Bean
    public String demoInitialization() {
        // Inicialización de datos demo para pruebas locales
        return "Demo data initialization bean registered";
    }
}