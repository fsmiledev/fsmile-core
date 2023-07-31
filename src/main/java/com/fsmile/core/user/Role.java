package com.fsmile.core.user;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:48 PM
 */
public record Role(
         String id,
         String name,
         String description,
         boolean composite,
         boolean clientRole,
         boolean containerId
) {
}
