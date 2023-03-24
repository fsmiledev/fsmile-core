package com.fsmile.admin.domain.donation;

import com.fsmile.core.domain.donation.api.Donation;
import com.fsmile.core.domain.donation.api.DonationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("donation/api/v1/")
public class DonationPrivateControllerV1 {

    private final DonationApi donationApi;

    public ResponseEntity<Map<String, String>> saveDonation(Donation donation) {
        String id = donationApi.addDonation(donation);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        return ResponseEntity.ok(map);
    }


}

