package com.fsmile.core.domain.donation.api;

import com.fsmile.core.domain.common.GenericApi;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;

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
    void validateDonation(String donationId, DonationStatus validate);
    void rejectDonation(String donationId, DonationStatus reject);
    void giveDonation(List<Donation> donations, String beneficiaryId);
    void confirmDonationGive(List<Donation> donations, DonationStatus recept);

    String addDonationCategory(DonationCategory category);
    void editDonationCategory(DonationCategory category);

    CompletableFuture<List<DonationCategory>> findAllValidatesCategories();
    CompletableFuture<List<DonationCategory>> findValidatesCategories();

    Page<Donation> findAllDonation(int page, int size) throws Exception;

}
