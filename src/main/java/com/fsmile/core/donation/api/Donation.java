package com.fsmile.core.donation.api;

import com.fsmile.core.user.api.User;
import lombok.Builder;

import java.util.Date;
import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
@Builder
public record Donation(
        String donationId,
        String donationName,
        User donor,
        DonationBeneficiary beneficiary,
        DonationStatus status,
        boolean isAnonymous,
        List<DonationImg> donationImgs,
        Date dateUpt,
        Date dateCreated,
        DonationCategory category) {
}



