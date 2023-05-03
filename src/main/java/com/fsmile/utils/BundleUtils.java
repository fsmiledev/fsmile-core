package com.fsmile.utils;

import com.sun.java.accessibility.util.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Project fsmile-core
 * Package com.fsmile.utils
 * Author revouna
 * Date 03/05/2023
 */

@Component
public class BundleUtils {
    private static ResourceBundleMessageSource messageSource;

    public BundleUtils(ResourceBundleMessageSource messageSource) {
        BundleUtils.messageSource = messageSource;
    }

    public static String message(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }
}
