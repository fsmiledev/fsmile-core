package com.fsmile.core.donation.impl;

import com.fsmile.core.donation.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.impl
 * Author revouna
 * Date 23/02/2023
 */

@Component
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
    public Donation getDonation(String donationId) {
        return null;
    }

    @Override
    public void validateDonation(String donationId) {
        donationRepository.validateDonation(donationId);
    }

    @Override
    public void rejectDonation(String donationId) {
        donationRepository.rejectDonation(donationId);

    }

    @Override
    public void giveDonation(List<String> donationIds, String beneficiaryId) {
        donationRepository.giveDonations(donationIds, beneficiaryId);
    }

    @Override
    public void confirmDonationGive(List<String> donationIds) {
        donationRepository.confirmDonationGive(donationIds);
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

    @Override
    public DonationBeneficiary getDonationBeneficiary(String beneficiaryId) {
        return donationRepository.getDonationBeneficiary(beneficiaryId);
    }

    @Override
    public Page<DonationBeneficiary> getAllDonationBeneficiaries(int page, int size) {
        return donationRepository.getAllDonationBeneficiaries(page, size);
    }
}
