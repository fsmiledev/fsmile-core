package com.fsmile.core.donation.api;

import lombok.Builder;

import java.util.Date;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
@Builder
public record DonationCategory(
        String categoryId,
        String categoryName
) {


}
