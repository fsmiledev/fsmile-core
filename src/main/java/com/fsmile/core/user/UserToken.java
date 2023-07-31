package com.fsmile.core.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

/**
 * Project fsmile-core
 * Package com.fsmile.core.domain.user.api
 * Author revouna
 * Date 23/03/2023
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record UserToken(
        String access_token,
        long expires_in,
        long refresh_expires_in,
        String refresh_token,
        String token_type,
        int not_before_policy,
        String session_state,
        String scope
) {
}
