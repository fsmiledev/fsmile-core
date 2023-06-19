package com.fsmile.app.language.mappers;

import com.fsmile.app.language.entities.LanguageEntity;
import com.fsmile.app.language.entities.TextEntity;
import com.fsmile.core.language.api.Language;
import com.fsmile.core.language.api.Text;

import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.mappers
 * Author revouna
 * Date 15/06/2023
 */
public class LanguageMapper {

    public static Language mapFromLanguageEntity(LanguageEntity languageEntity) {
        return Language.builder()
                .languageId(languageEntity.getLanguageId())
                .locale(languageEntity.getLocale())
                .enabled(languageEntity.isEnabled())
                .code(languageEntity.getCode())
                .createdDate(languageEntity.getCreatedDate())
                .build();
    }

    public static List<Language> mapFromLanguageEntities(List<LanguageEntity> languageEntities) {
       return languageEntities.stream().map(LanguageMapper::mapFromLanguageEntity).toList();
    }

    public static Text mapFromTextFromTextEntity(TextEntity textEntity) {
        return Text.builder()
                .textId(textEntity.getTextId())
                .languageId(textEntity.getLanguage().getLanguageId())
                .parentAttribute(textEntity.getParentAttribute())
                .wording(textEntity.getWording())
                .createdDate(textEntity.getCreatedDat())
                .build();
    }
    public static List<Text> mapFromTextFromTextEntities(List<TextEntity> textEntities) {
        return textEntities.stream().map(LanguageMapper::mapFromTextFromTextEntity).toList();
    }


}
