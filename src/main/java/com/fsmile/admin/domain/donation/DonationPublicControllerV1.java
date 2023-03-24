package com.fsmile.admin.domain.donation;

import com.fsmile.core.domain.donation.api.Donation;
import com.fsmile.core.domain.donation.api.DonationApi;
import com.fsmile.core.domain.donation.api.DonationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation
 * Author revouna
 * Date 07/03/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("donation/api/public/v1")
public class DonationPublicControllerV1 {

    private final DonationApi donationApi;

    @GetMapping("donation/{page}/{size}")
    public ResponseEntity<?> getDonationById(@PathVariable int donationId) throws Exception {
        return ResponseEntity.ok(donationApi);
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


}

