package com.fsmile.core.user;

import com.fsmile.core.authorization.Group;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:46 PM
 */
public interface UserService {
    Map<String, String> login(UserAuth userAuth) throws Exception;
    Map<String, String> generateAccessToken(String username, String password, String clientId);
    Map<String, String> generateAccessToken(String clientId, String refreshToken);
    Map<String, String> generateRefreshToken(String username, String clientId);
    String createUser(UserModel user);
    void updateProfile(UserModel user) throws Exception;
    void resetUserPassword(ResetPassword resetPassword) throws Exception;
    void addUserMoresInfos(UserMoresInfos moresInfos);
    void updateUserMoresInfos(UserMoresInfos moresInfos);
    UserModel getUser(String username);
    UserModel getUserByDonationId(String donationId) throws Exception;
    UserMoresInfos getUserMoresInfos(String userId);
    Page<UserModel> findAllUsers(int page, int size);
    void createUserSetting(UserSetting userSetting);
    void editUserSetting(UserSetting userSetting);
    Page<List<UserModel>> findByGroupId(String groupId);


}

