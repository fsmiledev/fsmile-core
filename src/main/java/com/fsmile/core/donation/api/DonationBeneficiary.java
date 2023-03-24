package com.fsmile.core.donation.api;

import java.util.Date;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
public record DonationBeneficiary(
        String beneficiaryId,
        String name,
        String description,
        String email,
        String rib,
        Long phoneNumber1,
        Long phoneNumber2,
        Long phoneNumber3,

        String addressLine1,
        String addressLine2,
        String addressLine3,

        String imgUrl,
        String doc,

        Date dateUpd,
        String userUpd

) {
}
