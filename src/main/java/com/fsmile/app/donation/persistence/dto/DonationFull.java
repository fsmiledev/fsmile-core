package com.fsmile.app.donation.persistence.dto;

import com.fsmile.core.donation.api.DonationCategory;
import com.fsmile.core.donation.api.DonationImg;
import com.fsmile.core.donation.api.DonationStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.app.donation.persistence.dto
 * Author revouna
 * Date 04/05/2023
 */

@Getter
public class DonationFull extends Donation{
    private final DonationCategory donationCategory;

    public DonationFull(String donationId, String donationName, DonationStatus status, boolean isAnonymous, List<DonationImg> donationImgs, Date dateCreated, DonationCategory donationCategory) {
        super(donationId, donationName, status, isAnonymous, donationImgs, dateCreated);
        this.donationCategory = donationCategory;
    }

    public DonationCategory donationCategory(){
        return donationCategory;
    }

}
