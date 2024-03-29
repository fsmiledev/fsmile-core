package com.fsmile.core.donation;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.api
 * Author revouna
 * Date 21/02/2023
 */
public interface DonationService {
    String addDonation(DonationModel donation);
    void deleteDonationImg(String imgId);

    void deleteDonation(String donationId);

    void editDonation(DonationModel donation);
    DonationModel getDonation(String donationId);

    void validateDonation(String donationId);

    void rejectDonation(String donationId);

    void giveDonations(List<String> donationIds, String beneficiaryId);

    void confirmDonationGive(List<String> donationIds);

    String addDonationCategory();

    void editDonationCategory(DonationCategory category);

    List<DonationCategory> findAllCategories();

    Page<DonationModel> findAllDonation(int page, int size) throws Exception;
    Page<DonationModel> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception;

    String addDonationBeneficiary(DonationBeneficiary beneficiary);
    DonationBeneficiary getDonationBeneficiary(String beneficiaryId);
    Page<DonationBeneficiary> getAllDonationBeneficiaries(int page, int size) throws Exception;

    String editDonationBeneficiary(DonationBeneficiary beneficiary);
}
