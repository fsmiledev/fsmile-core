package com.fsmile.core.domain.user.api;

import com.fsmile.common.enums.Language;
import com.fsmile.common.enums.Theme;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:52 PM
 */
public record UserMoresInfos(
        String userMoreInfosId,
        String userId,
        String imageCoverUrl,
        String dateCreated
        ) {
}
