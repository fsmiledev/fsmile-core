package com.fsmile.core.donation.impl;

import com.fsmile.core.donation.api.*;
import com.fsmile.core.language.api.Language;
import com.fsmile.core.language.api.LanguageTextService;
import com.fsmile.core.language.api.ParentAttribute;
import com.fsmile.core.language.api.Text;
import com.fsmile.utils.BundleUtils;
import com.fsmile.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.impl
 * Author revouna
 * Date 23/02/2023
 */

@Component
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationCore donationCore;
    private final LanguageTextService textService;


    @Override
    public String addDonation(DonationModel donation) {
        donationCore.addDonation(donation);
        textService.getEnableLanguage().forEach(language -> {
            Text text = Text.builder()
                    .textId(StringUtils.uuid())
                    .parentId(donation.donationId())
                    .parentAttribute(ParentAttribute.DONATION_NAME)
                    .language(language)
                    .wording(language.locale() == Locale.getDefault() ? donation.donationName(): "")
                    .build();
            textService.addText(text);
        });
        return donation.donationId();
    }

    @Override
    public void deleteDonationImg(String imgId) {
        donationCore.deleteDonationImg(imgId);
    }

    @Override
    public void deleteDonation(String donationId) {
        donationCore.deleteDonation(donationId);
    }

    @Override
    public void editDonation(DonationModel donation) {
        donationCore.editDonation(donation);
    }

    @Override
    public DonationModel getDonation(String donationId) {
        return donationCore.getDonation(donationId);
    }

    @Override
    public void validateDonation(String donationId) {
        donationCore.validateDonation(donationId);
    }

    @Override
    public void rejectDonation(String donationId) {
        donationCore.rejectDonation(donationId);
    }

    @Override
    public void giveDonation(List<String> donationIds, String beneficiaryId) {
        donationCore.giveDonations(donationIds, beneficiaryId);
    }

    @Override
    public void confirmDonationGive(List<String> donationIds) {
        donationCore.confirmDonationGive(donationIds);
    }

    @Override
    public String addDonationCategory(DonationCategory category) {
        return donationCore.addDonationCategory(category);
    }

    @Override
    public void editDonationCategory(DonationCategory category) {
        donationCore.editDonationCategory(category);
    }

    @Override
    public List<DonationCategory> findAllCategories() {
        return donationCore.findAllCategories();
    }

    @Override
    public Page<DonationModel> findAllDonation(int page, int size) throws Exception {
        return donationCore.findAllDonation(page, size);
    }

    @Override
    public Page<DonationModel> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception {
        return donationCore.findDonationsByStatus(page, size, status);
    }

    @Override
    public Map<String, String> addDonationBeneficiary(DonationBeneficiary beneficiary) {
        String id = donationCore.addDonationBeneficiary(beneficiary);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        response.put("message", BundleUtils.message("donation_beneficiary_added"));
        return response;
    }

    @Override
    public Map<String, String> editDonationBeneficiary(DonationBeneficiary beneficiary) {
        donationCore.addDonationBeneficiary(beneficiary);
        Map<String, String> response = new HashMap<>();
        response.put("message", BundleUtils.message("donation_beneficiary_edited"));
        return response;
    }

    @Override
    public DonationBeneficiary getDonationBeneficiary(String beneficiaryId) {
        return donationCore.getDonationBeneficiary(beneficiaryId);
    }

    @Override
    public Page<DonationBeneficiary> getAllDonationBeneficiaries(int page, int size) throws Exception {
        return donationCore.getAllDonationBeneficiaries(page, size);
    }
}
