package com.fsmile.core.authorization;

import lombok.Builder;

import java.util.Date;

/**
 * Project fsmile-core
 * Package com.fsmile.core.authorization
 * Author revouna
 * Date 10/08/2023
 */

@Builder
public record Client(String clientId,
                     String clientName,
                     boolean bearerOnly,
                     boolean publicClient,
                     String protocol,
                     Long accessTokenTimeout,
                     Long refreshTokenTimeout,
                     String rootUrl,
                     String baseUrl,
                     Date date) {
}
