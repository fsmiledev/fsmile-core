package com.fsmile.app.donation.persistence;

import com.fsmile.core.donation.api.DonationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation.persistence
 * Author revouna
 * Date 07/03/2023
 */
public interface DonationJpaRepository extends JpaRepository<DonationEntity, String> {

    @Async
    CompletableFuture<Page<DonationEntity>> findAllBy(Pageable pageable);
    @Async
    CompletableFuture<Page<DonationEntity>> findDonationEntityByStatus(DonationStatus status, Pageable pageable);
    @Async
    CompletableFuture<Page<DonationEntity>> findDonationEntitiesByCategoryOrderByCreatedDateAsc(DonationCategoryEntity category, Pageable pageable);
    @Async
    CompletableFuture<Page<DonationEntity>> findDonationEntitiesByCategoryAndStatusOrderByCreatedDateAsc(DonationCategoryEntity category, DonationStatus status, Pageable pageable);
}
