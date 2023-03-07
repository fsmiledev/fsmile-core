package com.fsmile.core.domain.donation.api;

import java.util.Date;

/**
 * Project fsmile
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
public record DonationImg(
        String imgId,
        String donationId,
        String imgUrl,
        Date dateUpdated,
        Date dateCreated
) {
}
