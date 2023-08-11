package com.fsmile.config.Security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Project fsmile-core
 * Package com.fsmile.config.Security
 * Author revouna
 * Date 07/08/2023
 */

@ConfigurationProperties(prefix = "rsa")
public record RsaKeyConfig(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
) {
}
