package com.fsmile.core.domain.donation.api;

import com.fsmile.core.domain.user.api.User;

import java.util.Date;
import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
public record Donation (
        String donationId,
        User donor,
        DonationBeneficiary beneficiary,
        DonationStatus status,
        boolean isAnonymous,
        List<DonationImg> donationImgs,
        Date dateUpt,
        Date dateCreated,
        DonationCategory category)  {}



