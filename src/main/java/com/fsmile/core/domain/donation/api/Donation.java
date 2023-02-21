package com.fsmile.core.domain.donation.api;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
public record Donation (
        String donationId,
        String donorEmail,
        DonationBeneficiary beneficiary,
        boolean isAnonymous,
        List<DonationImg> donationImgs,
        DonationCategory category)  {}



