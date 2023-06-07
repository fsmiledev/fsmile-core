package com.fsmile.app.donation.models;

import com.fsmile.core.donation.api.DonationCategory;
import com.fsmile.core.donation.api.DonationImg;
import com.fsmile.core.donation.api.DonationStatus;
import com.fsmile.core.language.api.Text;
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
    private final List<Text> donationNames;

    public DonationFull(String donationId, String donationName, DonationStatus status, boolean isAnonymous, List<DonationImg> donationImgs, Date dateCreated, DonationCategory donationCategory, List<Text> donationNames) {
        super(donationId, donationName, status, isAnonymous, donationImgs, dateCreated);
        this.donationCategory = donationCategory;
        this.donationNames = donationNames;
    }

    public DonationCategory donationCategory(){
        return donationCategory;
    }

    public List<Text> donationNames() {
        return donationNames;
    }

}
