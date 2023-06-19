package com.fsmile.core.donation.api;

import com.fsmile.core.language.api.Text;
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
public record DonationCategory(
        String categoryId,
        String categoryName,
        String slogan,
        List<Text> categoryNames,
        List<Text> slogans,
        String categoryImg
) {
}
