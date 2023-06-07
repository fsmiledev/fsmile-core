package com.fsmile.app.language;

import com.fsmile.app.language.entities.LanguageEntity;
import com.fsmile.app.language.entities.TextEntity;
import com.fsmile.app.language.repositories.LanguageJpaRepository;
import com.fsmile.app.language.repositories.TextJpaRepository;
import com.fsmile.core.language.api.Language;
import com.fsmile.core.language.api.LanguageCore;
import com.fsmile.core.language.api.ParentAttribute;
import com.fsmile.core.language.api.Text;
import com.fsmile.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language
 * Author revouna
 * Date 05/06/2023
 */

@Service
@RequiredArgsConstructor
public class LanguageCoreImpl implements LanguageCore {

    private final TextJpaRepository textRepository;
    private final LanguageJpaRepository languageJpaRepository;
    @Override
    public void saveText(Text text) {
        TextEntity textEntity = TextEntity.builder()
                .textId(text.textId() == null ? StringUtils.uuid() : text.textId())
                .parentId(text.parentId())
                .parentAttribute(text.parentAttribute())
                .wording(text.wording())
                .language(new LanguageEntity(text.languageId()))
                .build();
    }

    @Override
    public String findByParentAndLocale(String parentId, ParentAttribute parentAttribute, Locale locale) {
        return textRepository.findByParentAndLocale(parentId, parentAttribute, locale);
    }


    @Override
    public List<Text> findAllByParentId(String parentId, ParentAttribute parentAttribute) {
        return null;
    }

    @Override
    public void saveLanguage(Language language) {

    }

    @Override
    public void enableOrDisableLanguage() {

    }

    @Override
    public Language findLanguageById(String languageId) {
        return null;
    }

    @Override
    public List<Language> findEnableLanguages() {
        return null;
    }

    @Override
    public List<Language> findAllLanguages() {
        return null;
    }
}
