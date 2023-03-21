package com.fsmile.core.domain.user.api;

import com.fsmile.common.enums.Language;
import com.fsmile.common.enums.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

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
        String password
) {
}
