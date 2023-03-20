package com.fsmile.core.domain.donation.api;

import jakarta.persistence.Column;
import lombok.Builder;
import org.hibernate.annotations.UpdateTimestamp;

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
        String categoryName,
        Date createdDate,
        Date updatedDate
) {


}
