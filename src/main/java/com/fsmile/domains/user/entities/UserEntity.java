package com.fsmile.domains.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Project fsmile
 * Package com.fsmile.domains.user.entities;
 * Author revouna
 * Date 29/06/2023
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id @Column(name = "USER_ID")
    String userId;
    @Column(unique = true, name = "USERNAME")
    String username;
    @Column(name = "FIRSTNAME")
    String firstName;
    @Column(name = "LASTNAME")
    String lastName;
    @Column(unique = true, name = "EMAIL")
    String email;
    @Column(name = "PASSWORD")
    String password;
    @Column(name = "ENABLED")
    private boolean enabled;
    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    public UserEntity(String userId) {
        this.userId = userId;
    }
}
