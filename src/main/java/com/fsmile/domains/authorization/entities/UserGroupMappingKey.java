package com.fsmile.domains.authorization.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Project bank-auth
 * Package com.bank.auth.entities
 * Author revouna
 * Date 29/06/2023
 */


@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupMappingKey implements Serializable {
    private String groupId;
    private String userId;
}
