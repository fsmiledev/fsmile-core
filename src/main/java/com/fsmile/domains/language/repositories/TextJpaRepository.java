package com.fsmile.domains.language.repositories;

import com.fsmile.domains.language.entities.LanguageEntity;
import com.fsmile.domains.language.entities.TextEntity;
import com.fsmile.core.language.ParentAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.app.donation.repositories
 * Author revouna
 * Date 06/06/2023
 */
public interface TextJpaRepository extends JpaRepository<TextEntity, String> {

    @Query("SELECT t.wording FROM TextEntity t WHERE t.parentId = :parentId AND t.parentAttribute = :parentAttribute AND t.language.locale = :locale")
    String findByParentAndLocale(@Param("parentId") String parentId, @Param("parentAttribute") ParentAttribute parentAttribute, @Param("locale") Locale locale);

    TextEntity findTopByParentIdAndParentAttributeAndLanguage(String parentId, ParentAttribute parentAttribute, LanguageEntity language);

    List<TextEntity> findByParentIdAndParentAttributeOrderByCreatedDat(String parentId, ParentAttribute parentAttribute);
}
