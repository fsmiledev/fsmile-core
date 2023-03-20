package com.fsmile.admin.domain.donation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationCategoryEntityRepository extends JpaRepository<DonationCategoryEntity, String> {
}