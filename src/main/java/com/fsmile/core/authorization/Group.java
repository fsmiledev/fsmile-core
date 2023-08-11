package com.fsmile.core.authorization;

import lombok.Builder;

import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.core.authorization
 * Author revouna
 * Date 07/08/2023
 */

@Builder
public record Group(
        String groupId,
        String code,
        String name,
        List<Role> roles
) {
}
