package com.fsmile.domains.donation.controllers;

import com.fsmile.core.donation.DonationBeneficiary;
import com.fsmile.core.donation.DonationCategory;
import com.fsmile.core.donation.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation
 * Author revouna
 * Date 07/03/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/donation/private")
public class DonationPrivateControllerV1 {

    private final DonationService donationService;

    @GetMapping("donations/{page}/{size}")
    public ResponseEntity<?> getAllDonations( @PathVariable int page, @PathVariable int size) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(donationService.findAllDonation(page, size));
    }

    @PutMapping("validate-donation")
    public ResponseEntity<?> validateDonation(@RequestBody String donationId) {
        donationService.validateDonation(donationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("reject-donation")
    public ResponseEntity<?> rejectDonation(@RequestBody String donationId) {
        donationService.rejectDonation(donationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("give-donation/{beneficiaryId}")
    public ResponseEntity<?> giveDonation(@RequestBody List<String> donationIds, @PathVariable String beneficiaryId) {
        donationService.giveDonations(donationIds, beneficiaryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("confirm-give-donation")
    public ResponseEntity<?> confirmGiveDonation(@RequestBody List<String> donationIds) {
        donationService.confirmDonationGive(donationIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("add-donation-category")
    public ResponseEntity<?> addDonationCategory() {
        return ResponseEntity.status(HttpStatus.OK).body(donationService.addDonationCategory());
    }

    @PutMapping("edit-donation-category")
    public ResponseEntity<?> editDonationCategory(@RequestBody DonationCategory category) {
        donationService.editDonationCategory(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("add-donation-beneficiary")
    public ResponseEntity<?> addDonationBeneficiary(@RequestBody DonationBeneficiary beneficiary) {
        return ResponseEntity.status(HttpStatus.OK).body(donationService.addDonationBeneficiary(beneficiary));
    }

    @PutMapping("edit-donation-beneficiary")
    public ResponseEntity<?> editDonationBeneficiary(@RequestBody DonationBeneficiary beneficiary) {
        return ResponseEntity.status(HttpStatus.OK).body(donationService.editDonationBeneficiary(beneficiary));
    }
}

