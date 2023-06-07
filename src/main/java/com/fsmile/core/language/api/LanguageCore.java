package com.fsmile.core.language.api;

import java.util.List;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.core.language.api
 * Author revouna
 * Date 05/06/2023
 */

public interface LanguageCore {
    void saveText(Text text);
    String findByParentAndLocale(String parentId, ParentAttribute parentAttribute, Locale locale);
    List<Text> findAllByParentId(String parentId, ParentAttribute parentAttribute);
    void saveLanguage(Language language);
    void enableOrDisableLanguage();
    Language findLanguageById(String languageId);
    List<Language> findEnableLanguages();
    List<Language> findAllLanguages();

}
