package com.fsmile.core.language;

import com.fsmile.core.language.ParentAttribute;
import lombok.Builder;

import java.util.Date;

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
