package com.fsmile.core.donation.api;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.api
 * Author revouna
 * Date 21/02/2023
 */
public interface DonationApi {

    String addDonation(Donation donation);
    void  deleteDonationImg(String imgId);
    void deleteDonation(String donationId);
    void editDonation(Donation donation);
    Donation getDonation(String donationId);

    void validateDonation(String donationId, DonationStatus validate);
    void rejectDonation(String donationId, DonationStatus reject);
    void giveDonation(List<Donation> donations, String beneficiaryId);
    void confirmDonationGive(List<Donation> donations);

    String addDonationCategory(DonationCategory category);
    void editDonationCategory(DonationCategory category);

    List<DonationCategory> findAllCategories();
    Page<Donation> findAllDonation(int page, int size) throws Exception;
    Page<Donation> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception;

    DonationBeneficiary getDonationBeneficiary(String beneficiaryId);
    Page<DonationBeneficiary> getAllDonationBeneficiaries(int page, int size);


}
