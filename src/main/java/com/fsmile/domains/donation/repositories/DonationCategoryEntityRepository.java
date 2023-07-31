package com.fsmile.domains.donation.repositories;

import com.fsmile.domains.donation.entities.DonationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationCategoryEntityRepository extends JpaRepository<DonationCategoryEntity, String> {
}
