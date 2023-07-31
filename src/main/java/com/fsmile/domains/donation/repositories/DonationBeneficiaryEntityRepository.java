package com.fsmile.domains.donation.repositories;

import com.fsmile.domains.donation.entities.DonationBeneficiaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface DonationBeneficiaryEntityRepository extends JpaRepository<DonationBeneficiaryEntity, String> {
    @Async
    CompletableFuture<Page<DonationBeneficiaryEntity>> findAllByOrderByNameAsc(Pageable pageable);
}
