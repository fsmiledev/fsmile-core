package com.fsmile.domains.authorization.entities;

import com.fsmile.domains.user.entities.UserEntity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
public class UserGroupMapping {

    @EmbeddedId
    private UserGroupMappingKey key;
    @ManyToOne
    @MapsId("groupId")
    private GroupEntity groupEntity;
    @ManyToOne
    @MapsId("userId")
    private UserEntity user;
}
