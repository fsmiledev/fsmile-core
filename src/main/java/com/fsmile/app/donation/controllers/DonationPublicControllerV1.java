package com.fsmile.app.donation.controllers;

import com.fsmile.app.donation.models.AddDonation;
import com.fsmile.core.donation.api.DonationService;
import com.fsmile.core.donation.api.DonationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation
 * Author revouna
 * Date 07/03/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/donation/public")
public class DonationPublicControllerV1 {

    private final DonationService donationService;

    @GetMapping("donation/{donationId}")
    public ResponseEntity<?> getDonationById(@PathVariable String donationId) {
        return ResponseEntity.ok(donationService.getDonation(donationId));
    }

    @GetMapping("validate-donations/{page}/{size}")
    public ResponseEntity<?> getValidateDonation(@PathVariable int page, @PathVariable int size) throws Exception {
        return ResponseEntity.ok(donationService.findDonationsByStatus(page, size, DonationStatus.VALIDATE));
    }

    @GetMapping("received-donations/{page}/{size}")
    public ResponseEntity<?> getReceivedDonation(@PathVariable int page, @PathVariable int size) throws Exception {
        return ResponseEntity.ok(donationService.findDonationsByStatus(page, size, DonationStatus.RECEIVED));
    }

    @GetMapping("donations-category")
    public ResponseEntity<?> getDonationCategories() {
        return ResponseEntity.ok(donationService.findAllCategories());
    }

    @PostMapping("make-donation")
    public ResponseEntity<?> makeDonation(@RequestBody AddDonation donation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donationService.addDonation(donation));
    }

    @PutMapping("edit-donation")
    public ResponseEntity<?> editDonation(@RequestBody AddDonation donation) {
        donationService.editDonation(donation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete-donation/{donationId}")
    public ResponseEntity<?> deleteDonation(@PathVariable String donationId) {
        donationService.deleteDonation(donationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete-donation/{donationImgId}")
    public ResponseEntity<?> deleteDonationImg(@PathVariable String donationImgId) {
        donationService.deleteDonationImg(donationImgId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("donations-beneficiary/{beneficiaryId}")
    public ResponseEntity<?> getAllBeneficiary(@PathVariable String beneficiaryId) {
        return ResponseEntity.status(HttpStatus.OK).body(donationService.getDonationBeneficiary(beneficiaryId));
    }

    @GetMapping("donations-beneficiaries/{page}/{size}")
    public ResponseEntity<?> getAllBeneficiaries(@PathVariable int page, @PathVariable int size) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(donationService.getAllDonationBeneficiaries(page, size));
    }




}

