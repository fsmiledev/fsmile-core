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


    @Override
    public String addDonation(Donation donation) {
        donationRepository.addDonation(donation);
        return donation.donationId();
    }

    @Override
    public void deleteDonationImg(String imgId) {
        donationRepository.deleteDonationImg(imgId);
    }

    @Override
    public void deleteDonation(String donationId) {
        donationRepository.deleteDonation(donationId);
    }

    @Override
    public void editDonation(Donation donation) {
        donationRepository.editDonation(donation);
    }

    @Override
    public void validateDonation(String donationId, DonationStatus validate) {
        donationRepository.validateDonation(donationId, validate);
    }

    @Override
    public void rejectDonation(String donationId, DonationStatus reject) {
        donationRepository.rejectDonation(donationId, reject);

    }

    @Override
    public void giveDonation(List<Donation> donations, String beneficiaryId) {
        donationRepository.giveDonations(donations, beneficiaryId);
    }

    @Override
    public void confirmDonationGive(List<Donation> donations, DonationStatus recept) {
        donationRepository.confirmDonationGive(donations, recept);
    }

    @Override
    public String addDonationCategory(DonationCategory category) {
        return donationRepository.addDonationCategory(category);
    }

    @Override
    public void editDonationCategory(DonationCategory category) {
        donationRepository.editDonationCategory(category);
    }

    @Override
    public List<DonationCategory> findAllCategories() {
        return donationRepository.findAllCategories();
    }

    @Override
    public Page<Donation> findAllDonation(int page, int size) throws Exception {
        return donationRepository.findAllDonation(page, size);
    }

    @Override
    public Page<Donation> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception {
        return donationRepository.findDonationsByStatus(page, size, status);
    }
}