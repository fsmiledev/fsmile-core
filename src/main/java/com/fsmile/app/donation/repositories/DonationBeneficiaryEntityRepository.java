package com.fsmile.app.donation.repositories;

import com.fsmile.app.donation.entities.DonationBeneficiaryEntity;
import com.fsmile.app.donation.entities.DonationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface DonationBeneficiaryEntityRepository extends JpaRepository<DonationBeneficiaryEntity, String> {
    @Async
    CompletableFuture<Page<DonationBeneficiaryEntity>> findAllByOrderByNameAsc(Pageable pageable);
}
