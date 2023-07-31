package com.fsmile.core.user;

import com.fsmile.core.user.Language;
import com.fsmile.core.user.Theme;

import java.util.Date;

/**
 * Project fsmile
 * Package com.fsmile.core.domain.user.api
 * Author revouna
 * Date 21/02/2023
 */
public record UserSetting (

        String settingId,
        String userId,
        Language defaultLanguage,
        Theme defaultTheme,
        Date createdDate,
        boolean allowCookies) {

}
