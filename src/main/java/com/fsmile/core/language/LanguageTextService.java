package com.fsmile.core.language;

import java.util.List;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.core.language.api
 * Author revouna
 * Date 05/06/2023
 */

public interface LanguageTextService {
    String saveText(Text text);
    String edit(Text text);
    String findByParentAndLocale(String parentId, ParentAttribute parentAttribute, Locale locale);
    List<Text> findAllByParentId(String parentId, ParentAttribute parentAttribute);
    String translateText(String parentId, ParentAttribute parentAttribute, Locale locale);
    void saveLanguage(Language language);
    void enableOrDisableLanguage(String languageId);
    Language findLanguageById(String languageId);
    List<Language> findEnableLanguages();
    List<Language> findAllLanguages();

}
