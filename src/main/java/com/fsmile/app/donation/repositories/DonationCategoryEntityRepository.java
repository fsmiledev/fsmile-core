package com.fsmile.app.donation.repositories;

import com.fsmile.app.donation.entities.DonationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationCategoryEntityRepository extends JpaRepository<DonationCategoryEntity, String> {
}
