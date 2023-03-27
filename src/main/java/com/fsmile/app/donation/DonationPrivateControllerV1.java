package com.fsmile.app.donation;

import com.fsmile.core.donation.api.Donation;
import com.fsmile.core.donation.api.DonationApi;
import com.fsmile.core.donation.api.DonationCategory;
import com.fsmile.core.donation.api.DonationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation
 * Author revouna
 * Date 07/03/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/donation/public/donation")
public class DonationPrivateControllerV1 {

    private final DonationApi donationApi;

    @GetMapping("donations/{size}/{page}")
    public ResponseEntity<?> getAllDonations(@PathVariable int size, @PathVariable int page) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(donationApi.findAllDonation(size, page));
    }

    @PutMapping("validate-donation")
    public ResponseEntity<?> validateDonation(@RequestBody String donationId) {
        donationApi.validateDonation(donationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("reject-donation")
    public ResponseEntity<?> rejectDonation(@RequestBody String donationId) {
        donationApi.rejectDonation(donationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("give-donation/{beneficiaryId}")
    public ResponseEntity<?> giveDonation(@RequestBody List<String> donationIds, @PathVariable String beneficiaryId) {
        donationApi.giveDonation(donationIds, beneficiaryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("confirm-give-donation")
    public ResponseEntity<?> confirmGiveDonation(@RequestBody List<String> donationIds) {
        donationApi.confirmDonationGive(donationIds);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("add-donation-category")
    public ResponseEntity<?> addDonationCategory(@RequestBody DonationCategory category) {
        return ResponseEntity.status(HttpStatus.OK).body(donationApi.addDonationCategory(category));
    }

    @PutMapping("edit-donation-category")
    public ResponseEntity<?> editDonationCategory(@RequestBody DonationCategory category) {
        donationApi.editDonationCategory(category);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




}

