package com.fsmile.config.keycloack;

import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Project trunk
 * Package com.fsmile.config.keycloack
 * Author revouna
 * Date 21/03/2023
 */

@Component
public class KeycloakClientConfig {
    @Bean
    public Keycloak keycloak(@Value("${keycloak.server.url}") String serverUrl){
        return Keycloak.getInstance(serverUrl, "master", "lelu", "lelu","admin-cli");
    }

}
