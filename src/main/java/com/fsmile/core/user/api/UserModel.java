package com.fsmile.core.user.api;

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
    String password();
    String dateCreated();
}
