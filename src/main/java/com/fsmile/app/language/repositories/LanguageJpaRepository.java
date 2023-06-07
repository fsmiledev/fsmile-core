package com.fsmile.app.language.repositories;

import com.fsmile.app.language.entities.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.repositories
 * Author revouna
 * Date 06/06/2023
 */
public interface LanguageJpaRepository extends JpaRepository<LanguageEntity, String> {
}
