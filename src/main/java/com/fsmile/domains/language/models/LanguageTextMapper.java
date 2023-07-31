package com.fsmile.domains.language.models;

import com.fsmile.domains.language.entities.TextEntity;
import com.fsmile.core.language.Text;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.models
 * Author revouna
 * Date 07/06/2023
 */
public class LanguageTextMapper {


    public static Text mapTextEntityToTextDto(TextEntity text){
     return Text.builder()
             .textId(text.getTextId())
             .parentAttribute(text.getParentAttribute())
             .parentId(text.getParentId())
             .wording(text.getWording())
             .languageId(text.getLanguage().getLanguageId())
             .createdDate(text.getCreatedDat())
             .build();
    }
}
