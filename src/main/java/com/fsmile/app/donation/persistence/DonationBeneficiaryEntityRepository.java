package com.fsmile.app.donation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationBeneficiaryEntityRepository extends JpaRepository<DonationBeneficiaryEntity, String> {
}