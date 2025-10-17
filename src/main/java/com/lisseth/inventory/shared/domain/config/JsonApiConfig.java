package com.lisseth.inventory.shared.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class JsonApiConfig {

    @Bean
    public HttpMessageConverter<?> jsonApiConverter() {
        return new TypeConstrainedMappingJackson2HttpMessageConverter(Object.class);
    }
}
