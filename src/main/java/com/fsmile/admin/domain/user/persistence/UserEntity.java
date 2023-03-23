package com.fsmile.admin.domain.user.persistence;

import com.fsmile.common.enums.Language;
import com.fsmile.common.enums.Theme;
import com.fsmile.core.domain.user.api.Group;
import com.fsmile.core.domain.user.api.UserMoresInfos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:45 PM
 */

@Entity
@Table(name = "APP_USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
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

