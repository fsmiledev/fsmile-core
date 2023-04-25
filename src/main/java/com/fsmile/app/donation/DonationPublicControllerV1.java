package com.fsmile.app.donation;

import com.fsmile.core.donation.api.Donation;
import com.fsmile.core.donation.api.DonationApi;
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

    private final DonationApi donationApi;

    @GetMapping("donation/{donationId}")
    public ResponseEntity<?> getDonationById(@PathVariable String donationId) {
        return ResponseEntity.ok(donationApi.getDonation(donationId));
    }

    @GetMapping("validate-donations/{page}/{size}")
    public ResponseEntity<?> getValidateDonation(@PathVariable int page, @PathVariable int size) throws Exception {
        return ResponseEntity.ok(donationApi.findDonationsByStatus(page, size, DonationStatus.VALIDATE));
    }

    @GetMapping("received-donations/{page}/{size}")
    public ResponseEntity<?> getReceivedDonation(@PathVariable int page, @PathVariable int size) throws Exception {
        return ResponseEntity.ok(donationApi.findDonationsByStatus(page, size, DonationStatus.RECEIVED));
    }

    @GetMapping("donations-category")
    public ResponseEntity<?> getDonationCategories() {
        return ResponseEntity.ok(donationApi.findAllCategories());
    }

    @PostMapping("make-donation")
    public ResponseEntity<?> makeDonation(@RequestBody Donation donation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donationApi.addDonation(donation));
    }

    @PutMapping("edit-donation")
    public ResponseEntity<?> editDonation(@RequestBody Donation donation) {
        donationApi.editDonation(donation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete-donation/{donationId}")
    public ResponseEntity<?> deleteDonation(@PathVariable String donationId) {
        donationApi.deleteDonation(donationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete-donation/{donationImgId}")
    public ResponseEntity<?> deleteDonationImg(@PathVariable String donationImgId) {
        donationApi.deleteDonationImg(donationImgId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("donations-beneficiary/{beneficiaryId}")
    public ResponseEntity<?> getAllBeneficiary(@PathVariable String beneficiaryId) {
        return ResponseEntity.status(HttpStatus.OK).body(donationApi.getDonationBeneficiary(beneficiaryId));
    }

    @GetMapping("donations-beneficiaries/{page}/{size}")
    public ResponseEntity<?> getAllBeneficiaries(@PathVariable int page, @PathVariable int size) {
        return ResponseEntity.status(HttpStatus.OK).body(donationApi.getAllDonationBeneficiaries(page, size));
    }




}

