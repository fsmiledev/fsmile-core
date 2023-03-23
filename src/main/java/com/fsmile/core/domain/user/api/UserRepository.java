package com.fsmile.core.domain.user.api;

import com.fsmile.core.domain.common.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:46 PM
 */
public interface UserRepository {
    UserToken login(UserAuth userAuth) throws Exception;
    String createUser(User user);
    void updateProfile(User user) throws Exception;
    void resetUserPassword(ResetPassword resetPassword) throws Exception;
    void addUserMoresInfos(UserMoresInfos moresInfos);
    void updateUserMoresInfos(UserMoresInfos moresInfos);
    User getUserByEmail(String email) throws Exception;
    UserMoresInfos getUserMoresInfos(String userId);
    List<Role> getUserRoles(String userId);
    Page<User> findAllUsers(int page, int size);
    void createUserSetting(UserSetting userSetting);
    void editUserSetting(UserSetting userSetting);
    void createUserConnection(UserConnection connection);


}

