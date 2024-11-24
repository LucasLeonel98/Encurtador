package com.lucas.Encurtador.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permitir todas as rotas
                .allowedOrigins("http://localhost:4200")  // Origem permitida (substitua pelo seu frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("Authorization", "Content-Type")  // Cabeçalhos permitidos
                .allowCredentials(true);  // Permitir cookies, caso necessário
    }
}
