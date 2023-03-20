package com.fsmile.admin.domain.user.persistence;

import com.fsmile.core.domain.user.api.User;
import com.fsmile.core.domain.user.api.UserRepository;
import com.fsmile.utils.MapUtils;
import com.fsmile.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.admin.domain.user.persistence
 * @date 2/19/23 : 7:00 PM
 */

@Service
@RequiredArgsConstructor
public class UserDao implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    @Override
    public String create(User user) {
        UserEntity userEntity = UserEntity.builder()
                .userId(StringUtils.uuid())
                .email(user.email())
                .username(user.username())
                .password(user.password())
                .build();
        userJpaRepository.save(userEntity);
        return userEntity.getUserId();
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(String s) {
    }

    @Override
    public User find(String s) {
        return null;
    }


    @Override
    public Page<User> findAllBy(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<UserEntity>> userEntities = userJpaRepository.findAllBy(pageable);
        return null;
    }
}
