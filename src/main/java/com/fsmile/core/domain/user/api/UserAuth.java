package com.fsmile.core.domain.user.api;

/**
 * Project fsmile-core
 * Package com.fsmile.core.domain.user.api
 * Author revouna
 * Date 23/03/2023
 */
public record UserAuth(
        String username,
        String password,
        String grandType, //password Or refresh_token
        String refreshToken
) {
}
