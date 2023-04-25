package com.fsmile.app.user.persistence;


import lombok.Getter;

import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.app.user.persistence
 * Author revouna
 * Date 25/04/2023
 */

@Getter
public class UserRole extends User{
    private final List<String> roles;

    public UserRole(String userId, String username, String lastName, String email, List<String> role) {
        super(userId, username, lastName, email);
        this.roles = role;
    }

    public List<String> roles() {
        return roles;
    }


}

