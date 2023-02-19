package com.fsmile.core.domain.user.impl;

import com.fsmile.core.domain.user.api.User;
import com.fsmile.core.domain.user.api.UserApi;
import com.fsmile.core.domain.user.api.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.impl
 * @date 2/18/23 : 7:44 PM
 */

@RequiredArgsConstructor
public class UserApiImpl implements UserApi {

    private final UserRepository userRepository;
    @Override
    public String create(User user) {
        return null;
    }

    @Override
    public String update(User user, String s) {
        return null;
    }

    @Override
    public String delete(String s) {
        return null;
    }

    @Override
    public User find(String s) {
        return null;
    }

    @Override
    public Page<User> findAll(int page, int size) throws Exception {
        return userRepository.findAllBy(page, size);
    }


}
