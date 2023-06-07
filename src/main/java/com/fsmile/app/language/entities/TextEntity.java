package com.fsmile.app.language.entities;

import com.fsmile.core.language.api.Language;
import com.fsmile.core.language.api.ParentAttribute;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Project fsmile-core
 * Package com.fsmile.app.language.entities
 * Author revouna
 * Date 06/06/2023
 */

@Entity
@Table(name = "TEXT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TextEntity {
    @Id
    private String textId;
    private String parentId;
    private String wording;
    @Enumerated(EnumType.STRING)
    private ParentAttribute parentAttribute;

    @ManyToOne
    private LanguageEntity language;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDat;
}
