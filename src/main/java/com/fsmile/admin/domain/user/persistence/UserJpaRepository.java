package com.fsmile.admin.domain.user.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.admin.domain.user.persistence
 * @date 2/19/23 : 6:58 PM
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    @Async
    CompletableFuture<Page<UserEntity>> findAllBy(Pageable pageable);

    UserEntity findByEmail(String email);


}
