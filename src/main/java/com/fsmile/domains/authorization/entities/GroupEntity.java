package com.fsmile.domains.authorization.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class GroupEntity {
    @Id
    private String groupId;
    private String code;
    private String name;
    @OneToMany(mappedBy = "groupEntity")
    private List<RoleEntity> roleEntities;

    public GroupEntity(String groupId) {
        this.groupId = groupId;
    }
}
