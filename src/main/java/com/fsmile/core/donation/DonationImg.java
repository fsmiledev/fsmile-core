package com.fsmile.core.donation;

import lombok.Builder;

import java.util.Date;

/**
 * Project fsmile
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
@Builder
public record DonationImg(
        String imgId,
        String imgUrl
) {
}
