package com.fsmile.app.language;

import com.fsmile.app.language.entities.LanguageEntity;
import com.fsmile.app.language.entities.TextEntity;
import com.fsmile.app.language.mappers.LanguageMapper;
import com.fsmile.app.language.repositories.LanguageJpaRepository;
import com.fsmile.app.language.repositories.TextJpaRepository;
import com.fsmile.core.language.api.Language;
import com.fsmile.core.language.api.LanguageCore;
import com.fsmile.core.language.api.ParentAttribute;
import com.fsmile.core.language.api.Text;
import com.fsmile.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
        textRepository.save(textEntity);
    }

    @Override
    public String findByParentAndLocale(String parentId, ParentAttribute parentAttribute, Locale locale) {
        return textRepository.findByParentAndLocale(parentId, parentAttribute, locale);
    }


    @Override
    public List<Text> findAllByParentId(String parentId, ParentAttribute parentAttribute) {
        List<TextEntity> textEntities = textRepository.findByParentIdAndParentAttributeOrderByCreatedDat(parentId, parentAttribute);
        return LanguageMapper.mapFromTextFromTextEntities(textEntities);
    }

    @Override
    public void saveLanguage(Language language) {
        LanguageEntity languageEntity = LanguageEntity.builder()
                .languageId(language.languageId() == null ? StringUtils.uuid() : language.languageId())
                .code(language.code())
                .enabled(false)
                .locale(language.locale())
                .build();
        languageJpaRepository.save(languageEntity);
    }

    @Override
    public void enableOrDisableLanguage(String languageId) {
        LanguageEntity languageEntity = languageJpaRepository.getReferenceById(languageId);
        languageEntity.setEnabled(!languageEntity.isEnabled());
        languageJpaRepository.save(languageEntity);
    }

    @Override
    public Language findLanguageById(String languageId) {
        LanguageEntity languageEntity = languageJpaRepository.getReferenceById(languageId);
        return LanguageMapper.mapFromLanguageEntity(languageEntity);
    }

    @Override
    public List<Language> findEnableLanguages() {
        List<LanguageEntity> languageEntities = languageJpaRepository.findByEnabledOrderByCodeAsc(true);
        return LanguageMapper.mapFromLanguageEntities(languageEntities);
    }

    @Override
    public List<Language> findAllLanguages() {
        List<LanguageEntity> languageEntities = languageJpaRepository.findAll();
        return LanguageMapper.mapFromLanguageEntities(languageEntities);
    }
}
