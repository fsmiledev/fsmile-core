package com.fsmile.domains.language;

import com.fsmile.domains.language.entities.LanguageEntity;
import com.fsmile.domains.language.entities.TextEntity;
import com.fsmile.domains.language.mappers.LanguageMapper;
import com.fsmile.domains.language.repositories.LanguageJpaRepository;
import com.fsmile.domains.language.repositories.TextJpaRepository;
import com.fsmile.core.language.Language;
import com.fsmile.core.language.LanguageTextService;
import com.fsmile.core.language.ParentAttribute;
import com.fsmile.core.language.Text;
import com.fsmile.shared.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language
 * Author revouna
 * Date 05/06/2023
 */

@Service
@RequiredArgsConstructor
@Transactional
public class LanguageTextServiceImpl implements LanguageTextService {

    private final TextJpaRepository textRepository;
    private final LanguageJpaRepository languageJpaRepository;
    @Override
    public String saveText(Text text) {
        TextEntity checkedText = textRepository.findTopByParentIdAndParentAttributeAndLanguage(text.parentId(), text.parentAttribute(), new LanguageEntity(text.languageId()));
        if (checkedText != null) {
            throw new RuntimeException("Wording for this attribute in this language already exist");
        }
        String id =  StringUtils.uuid();
        TextEntity textEntity = TextEntity.builder()
                .textId(StringUtils.uuid())
                .parentId(text.parentId())
                .parentAttribute(text.parentAttribute())
                .wording(text.wording())
                .language(new LanguageEntity(text.languageId()))
                .build();
        textRepository.save(textEntity);
        return id;
    }

    @Override
    public String edit(Text text) {
        TextEntity textEntityChecked = textRepository.findById(text.textId()).orElseThrow(() -> new RuntimeException("Error during operation"));
        TextEntity textEntity = TextEntity.builder()
                .textId(text.textId())
                .parentId(text.parentId())
                .parentAttribute(text.parentAttribute())
                .wording(text.wording())
                .language(new LanguageEntity(text.languageId()))
                .build();
        textRepository.save(textEntity);
        return text.textId();
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
    public String translateText(String parentId, ParentAttribute parentAttribute, Locale locale) {
        List<Language> languages = findEnableLanguages();
        Optional<Boolean> checkLocal = languages.stream().map(l -> l.locale().equals(locale)).findFirst();
        Locale finalLocale  = checkLocal.isPresent() ? locale : Locale.ENGLISH;
        return findByParentAndLocale(parentId, parentAttribute, finalLocale);
    }

    @Override
    public void saveLanguage(Language language) {
        LanguageEntity languageEntity = LanguageEntity.builder()
                .languageId(language.languageId() == null ? StringUtils.uuid() : language.languageId())
                .code(language.code())
                .enabled(language.enabled())
                .locale(language.locale())
                .wording(language.wording())
                .logoUrl(language.logoUrl())
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
