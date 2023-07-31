package com.fsmile.domains.user.repositories;

import com.fsmile.domains.user.entities.UserEntity;
import com.fsmile.domains.user.models.User;
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
    
    @Query("SELECT u.donor FROM DonationEntity u WHERE u.donationId =: donationId")
    User findByDonation(@Param("donationId") String donationId);

    UserEntity findByEmail(String email);


}
