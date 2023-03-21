package com.fsmile.core.domain.user.impl;

import com.fsmile.core.domain.user.api.User;
import com.fsmile.core.domain.user.api.UserApi;
import com.fsmile.core.domain.user.api.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.impl
 * @date 2/18/23 : 7:44 PM
 */

@Component
@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserRepository userRepository;


    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public Page<User> findAllUsers(int page, int size) {
        return userRepository.findAllUsers(page, size);
    }
}
