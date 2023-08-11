package com.fsmile.domains.user.models;

import com.fsmile.core.user.UserModel;
import lombok.*;

import java.util.Date;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:45 PM
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserModel {

    protected String userId;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Date dateCreated;


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
    public Date dateCreated() {
        return dateCreated;
    }

}
