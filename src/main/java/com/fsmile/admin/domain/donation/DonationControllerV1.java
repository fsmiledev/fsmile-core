package com.fsmile.admin.domain.donation;

import com.fsmile.core.domain.donation.api.Donation;
import com.fsmile.core.domain.donation.api.DonationApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation
 * Author revouna
 * Date 07/03/2023
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("donation/api/v1/")
public class DonationControllerV1 {

    private final DonationApi donationApi;

    public ResponseEntity<Map<String, String>> saveDonation(Donation donation) {
        String id = donationApi.addDonation(donation);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        return ResponseEntity.ok(map);
    }


}

