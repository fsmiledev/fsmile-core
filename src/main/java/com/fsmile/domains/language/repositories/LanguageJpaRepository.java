package com.fsmile.domains.language.repositories;

import com.fsmile.domains.language.entities.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.repositories
 * Author revouna
 * Date 06/06/2023
 */
public interface LanguageJpaRepository extends JpaRepository<LanguageEntity, String> {
    List<LanguageEntity> findByEnabledOrderByCodeAsc(boolean enabled);
}
