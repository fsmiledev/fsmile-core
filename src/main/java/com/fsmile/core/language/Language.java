package com.fsmile.core.language;

import lombok.Builder;

import java.util.Date;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.core.language.api
 * Author revouna
 * Date 05/06/2023
 */

@Builder
public record Language(
        String languageId,
        Locale locale,
        String code,
        boolean enabled,
        String logoUrl,
        String wording,
        Date createdDate
) {
}
