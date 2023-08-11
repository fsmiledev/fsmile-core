package com.fsmile.domains.authorization.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

/**
 * Project fsmile-core
 * Package com.fsmile.domains.authorization.entities
 * Author revouna
 * Date 10/08/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "APP_CLIENT")
public class ClientEntity {

    @Id
    private String clientId;
    private String clientName;
    private boolean bearerOnly;
    private boolean publicClient;
    private String protocol;
    private Long accessTokenTimeout;
    private Long refreshTokenTimeout;
    private String rootUrl;
    private String baseUrl;
    @CreationTimestamp
    private Date date;

}
