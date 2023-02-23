package com.fsmile.core.domain.user.api;

import com.fsmile.common.enums.Language;
import com.fsmile.common.enums.Theme;

/**
 * Project fsmile
 * Package com.fsmile.core.domain.user.api
 * Author revouna
 * Date 21/02/2023
 */
public record UserSetting (
        Language defaultLanguage,
        Theme defaultTheme,
        boolean allowCookies
) {

}
