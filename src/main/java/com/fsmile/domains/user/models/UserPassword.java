package com.fsmile.domains.user.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Project fsmile-core
 * Package com.fsmile.domains.user.models
 * Author revouna
 * Date 11/08/2023
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPassword extends User {
    private String password;

    public UserPassword(String userId, String firstName, String lastName, String email, String username, String password, Date dateCreated) {
        super(userId, firstName, lastName, email, username, dateCreated);
        this.password = password;
    }

    public String password() {
        return password;
    }
}
