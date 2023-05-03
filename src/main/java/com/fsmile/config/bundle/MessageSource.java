package com.fsmile.config.bundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Project fsmile-core
 * Package com.fsmile.config.web
 * Author revouna
 * Date 03/05/2023
 */

@Configuration("bundleMessageSource")
public class MessageSource {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }


}
