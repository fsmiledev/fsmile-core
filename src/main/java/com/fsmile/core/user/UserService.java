package com.fsmile.core.user;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:46 PM
 */
public interface UserService {
    UserToken login(UserAuth userAuth) throws Exception;
    String createUser(UserModel user);
    void updateProfile(UserModel user) throws Exception;
    void resetUserPassword(ResetPassword resetPassword) throws Exception;
    void addUserMoresInfos(UserMoresInfos moresInfos);
    void updateUserMoresInfos(UserMoresInfos moresInfos);
    UserModel getUserByEmail(String email) throws Exception;
    UserModel getUserByDonationId(String donationId) throws Exception;
    UserMoresInfos getUserMoresInfos(String userId);
    List<Role> getUserRoles(String userId);
    Page<UserModel> findAllUsers(int page, int size);
    void createUserSetting(UserSetting userSetting);
    void editUserSetting(UserSetting userSetting);
    void createUserConnection(UserConnection connection);


}

