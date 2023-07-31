package com.fsmile.domains.user.models;

import com.fsmile.core.user.UserModel;
import lombok.*;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:45 PM
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User implements UserModel {

    protected String userId;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String dateCreated;

    public User(String userId, String username, String lastName, String email) {
        this.userId = userId;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
    }


    @Override
    public String userId() {
        return userId;
    }

    @Override
    public String username() {
        return username;
    }

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String dateCreated() {
        return dateCreated;
    }

}
