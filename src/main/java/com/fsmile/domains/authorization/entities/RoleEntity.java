package com.fsmile.domains.authorization.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Project bank-auth
 * Package com.bank.auth.entities
 * Author revouna
 * Date 29/06/2023
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoleEntity {
    @Id
    private String roleId;
    private String code;
    private String name;
    @ManyToOne
    private GroupEntity groupEntity;
}
