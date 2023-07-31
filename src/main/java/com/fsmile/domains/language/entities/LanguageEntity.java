package com.fsmile.domains.language.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Locale;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.entities
 * Author revouna
 * Date 06/06/2023
 */

@Entity
@Table(name = "LANGUAGE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LanguageEntity {

    @Id
    private String languageId;
    private Locale locale;
    private String code;
    private boolean enabled;
    String logoUrl;
    String wording;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    public LanguageEntity(String languageId) {
        this.languageId = languageId;
    }
}
