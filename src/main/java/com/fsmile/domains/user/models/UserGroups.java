package com.fsmile.domains.user.models;


import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.app.user.persistence
 * Author revouna
 * Date 25/04/2023
 */

@Getter
public class UserGroups extends User {
    private final List<String> groups;

    public UserGroups(String userId, String firstName, String lastName, String email, String username, Date dateCreated, List<String> groups) {
        super(userId, firstName, lastName, email, username, dateCreated);
        this.groups = groups;
    }

    public List<String> groups() {
        return groups;
    }


}

