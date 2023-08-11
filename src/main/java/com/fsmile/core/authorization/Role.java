package com.fsmile.core.authorization;

import lombok.Builder;

/**
 * Project fsmile-core
 * Package com.fsmile.core.authorization
 * Author revouna
 * Date 07/08/2023
 */

@Builder
public record Role(
        String roleId,
        String code,
        String name,
        String groupId
) {
}
