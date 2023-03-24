package com.fsmile.config.exceptions;

import org.springframework.http.HttpStatusCode;

/**
 * Project fsmile-core
 * Package com.fsmile.config.exceptions
 * Author revouna
 * Date 24/03/2023
 */
public record Error(
        HttpStatusCode code,
        String message
) {
}
