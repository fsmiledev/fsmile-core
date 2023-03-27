package com.fsmile.app.user.persistence;

import com.fsmile.core.user.api.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query("SELECT u FROM UserEntity u WHERE u.userId IN (SELECT d FROM DonationEntity d WHERE d.donor.userId := donationId)")
    User findByDonation(@Param("donationId") String donationId);

    UserEntity findByEmail(String email);


}
