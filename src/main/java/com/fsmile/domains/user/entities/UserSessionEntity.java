package com.fsmile.domains.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Project fsmile
 * Package com.fsmile.domains.user.entities
 * Author revouna
 * Date 29/06/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSessionEntity {
    @Id
    private String sessionId;
    private String ipAddress;
    private boolean rememberMe;
    private String authMethod;
    @CreationTimestamp
    @Column(nullable = false)
    private Date date;
    @ManyToOne
    private UserEntity userEntity;

}
