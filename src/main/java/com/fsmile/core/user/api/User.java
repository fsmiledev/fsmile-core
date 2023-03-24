package com.fsmile.core.user.api;

import lombok.Builder;

import java.util.List;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:45 PM
 */

@Builder
public record User(
        String userId,
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        String dateCreated,
        List<String> roles

) {
}
