package com.fsmile.core.domain.donation.api;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.api
 * Author revouna
 * Date 21/02/2023
 */
public interface DonationRepository {
    void addDonation(Donation donation);
    void deleteDonationImg(String imgId);

    void deleteDonation(String donationId);

    void editDonation(Donation donation);

    void validateDonation(String donationId, DonationStatus validate);

    void rejectDonation(String donationId, DonationStatus reject);

    void giveDonations(List<Donation> donations, String beneficiaryId);

    void confirmDonationGive(List<Donation> donations);

    String addDonationCategory(DonationCategory category);

    void editDonationCategory(DonationCategory category);

    List<DonationCategory> findAllCategories();

    Page<Donation> findAllDonation(int page, int size) throws Exception;
    Page<Donation> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception;

}
