package com.fsmile.core.language.api;

import java.util.List;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.core.language.api
 * Author revouna
 * Date 05/06/2023
 */
public interface LanguageTextService {
    void addText(Text text);
    void editText(Text text);
    String translateText(String parentId, ParentAttribute parentAttribute, Locale locale);
    List<Text> getTextByParentId(String parentId, ParentAttribute parentAttribute);
    void addLanguage(Language language);
    void enableOrDisableLanguage(String languageId);
    Language getLanguage(String languageId);
    List<Language> getEnableLanguage();
    List<Language> getAllLanguage();
}
