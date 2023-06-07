package com.fsmile.core.user.impl;

import com.fsmile.core.user.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.impl
 * @date 2/18/23 : 7:44 PM
 */

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserToken login(UserAuth userAuth) throws Exception {
        return userRepository.login(userAuth);
    }

    @Override
    public String  createUser(UserModel user) {
        return userRepository.createUser(user);
    }

    @Override
    public void updateProfile(UserModel user) throws Exception {
        userRepository.updateProfile(user);
    }

    @Override
    public void resetUserPassword(ResetPassword resetPassword) throws Exception {
        userRepository.resetUserPassword(resetPassword);
    }

    @Override
    public void addUserMoresInfos(UserMoresInfos moresInfos) {
        userRepository.addUserMoresInfos(moresInfos);
    }

    @Override
    public void updateUserMoresInfos(UserMoresInfos moresInfos) {
        userRepository.updateUserMoresInfos(moresInfos);
    }

    @Override
    public UserModel getUserByEmail(String email) throws Exception {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public UserModel getUserByDonationId(String donationId) throws Exception {
        return userRepository.getUserByDonationId(donationId);
    }

    @Override
    public UserMoresInfos getUserMoresInfos(String userId) {
        return userRepository.getUserMoresInfos(userId);
    }

    @Override
    public List<Role> getUserRoles(String userId) {
        return null;
    }

    @Override
    public Page<UserModel> findAllUsers(int page, int size) {
        return userRepository.findAllUsers(page, size);
    }

    @Override
    public void createUserSetting(UserSetting userSetting) {
        userRepository.createUserSetting(userSetting);
    }

    @Override
    public void editUserSetting(UserSetting userSetting) {
        userRepository.editUserSetting(userSetting);
    }
}
