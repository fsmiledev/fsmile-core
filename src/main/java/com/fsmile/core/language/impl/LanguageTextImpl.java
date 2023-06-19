package com.fsmile.core.language.impl;

import com.fsmile.app.language.entities.LanguageEntity;
import com.fsmile.core.language.api.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Project fsmile-core
 * Package com.fsmile.core.language.impl
 * Author revouna
 * Date 05/06/2023
 */

@Component
@AllArgsConstructor
public class LanguageTextImpl implements LanguageTextService {

    private final LanguageCore languageCore;
    @Override
    public void addText(Text text){
        languageCore.saveText(text);
    }

    @Override
    public void editText(Text text) {
        languageCore.saveText(text);
    }

    @Override
    public String translateText(String parentId, ParentAttribute parentAttribute, Locale locale) {
        List<Language> languages = getEnableLanguage();
        Optional<Boolean> first = languages.stream().map(l -> l.locale().equals(locale)).findFirst();
        Locale finalLocale  = first.isPresent() ? locale : Locale.ENGLISH;
        return languageCore.findByParentAndLocale(parentId, parentAttribute, finalLocale);
    }

    @Override
    public List<Text> getTextByParentId(String parentId, ParentAttribute parentAttribute) {
        return languageCore.findAllByParentId(parentId, parentAttribute);
    }

    @Override
    public void addLanguage(Language language) {
        languageCore.saveLanguage(language);
    }

    @Override
    public void enableOrDisableLanguage(String languageId) {
        languageCore.enableOrDisableLanguage(languageId);
    }

    @Override
    public Language getLanguage(String languageId) {
        return languageCore.findLanguageById(languageId);
    }

    @Override
    public List<Language> getEnableLanguage() {
        return languageCore.findEnableLanguages();
    }

    @Override
    public List<Language> getAllLanguage() {
        return languageCore.findAllLanguages();
    }
}
