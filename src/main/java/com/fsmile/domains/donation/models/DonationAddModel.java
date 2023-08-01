package com.fsmile.domains.donation.models;

import com.fsmile.core.donation.DonationImg;
import com.fsmile.core.donation.DonationStatus;
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
public class DonationAddModel extends Donation{
    private String donorId;
    private String categoryId;
    public DonationAddModel() {
    }

    public DonationAddModel(String donationId, String donationName, DonationStatus status, Boolean isAnonymous, List<DonationImg> donationImgs, Date dateCreated, String donorId, String categoryId) {
        super(donationId, donationName, status, isAnonymous, donationImgs, dateCreated);
        this.donorId = donorId;
        this.categoryId = categoryId;
    }

    public String donorId() {
        return donorId;
    }
    public String categoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return "AddDonation{" +
                "donorId='" + donorId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", donationId='" + donationId + '\'' +
                ", donationName='" + donationName + '\'' +
                ", enabled=" + status +
                ", isAnonymous=" + isAnonymous +
                ", donationImgs=" + donationImgs +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
