package com.fsmile.core.domain.user.api;

import lombok.Builder;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 3/23/23 : 3:37 AM
 */

@Builder
public record UserConnection(
        String connectionId,
        String userId,
        String country,
        String userAgent,
        String ipAddress,
        String connectionDate
) {
}
