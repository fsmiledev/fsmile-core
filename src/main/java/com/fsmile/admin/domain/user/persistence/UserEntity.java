package com.fsmile.admin.domain.user.persistence;

import com.fsmile.common.enums.Language;
import com.fsmile.common.enums.Theme;
import com.fsmile.core.domain.user.api.Group;
import com.fsmile.core.domain.user.api.UserMoresInfos;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:45 PM
 */

@Entity
@Table(name = "USER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    String userId;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    public UserEntity(String userId) {
        this.userId = userId;
    }

}

