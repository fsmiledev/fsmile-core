package com.fsmile.core.user;

import java.util.Date;
import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.core.user.api
 * Author revouna
 * Date 25/04/2023
 */
public interface UserModel {
    String userId();
    String username();
    String firstName();
    String lastName();
    String email();
    Date dateCreated();
}
