package com.fsmile.core.language.api;

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
public record Text(
        String textId,
        String parentId,
        String wording,
        ParentAttribute parentAttribute,
        String languageId,
        Date createdDate
) {
}
