package com.fsmile.core.domain.donation.impl;

import com.fsmile.core.domain.donation.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.impl
 * Author revouna
 * Date 23/02/2023
 */

@RequiredArgsConstructor
public class DonationApiImpl implements DonationApi {

    private final DonationRepository donationRepository;
    private final DonationBeneficiaryRepository donationBeneficiaryRepository;
    private final DonationImgRepository donationImgRepository;

    @Override
    public String addDonation(Donation donation) {
        donationRepository.create(donation);
        donationImgRepository.create(donation.donationImgs());
        return donation.donationId();
    }

    @Override
    public void deleteDonationImg(String imgId) {
        donationImgRepository.delete(imgId);
    }

    @Override
    public void deleteDonation(String donationId) {
        donationRepository.delete(donationId);
    }

    @Override
    public void editDonation(Donation donation) {
        donationRepository.update(donation);
    }

    @Override
    public void validateDonation(String donationId, DonationStatus validate) {
    }

    @Override
    public void rejectDonation(String donationId, DonationStatus reject) {

    }

    @Override
    public void giveDonation(List<Donation> donations, String beneficiaryId) {

    }

    @Override
    public void confirmDonationGive(List<Donation> donations, DonationStatus recept) {

    }

    @Override
    public String addDonationCategory(DonationCategory category) {
        return null;
    }

    @Override
    public void editDonationCategory(DonationCategory category) {

    }

    @Override
    public CompletableFuture<List<DonationCategory>> findAllValidatesCategories() {

        return null;
    }

    @Override
    public CompletableFuture<List<DonationCategory>> findValidatesCategories() {
        return null;
    }

    @Override
    public Page<Donation> findAllDonation(int page, int size) throws Exception {

        return donationRepository.findAllBy(page, size);
    }
}
