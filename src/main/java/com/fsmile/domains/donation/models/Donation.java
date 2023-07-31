package com.fsmile.domains.donation.models;

import com.fsmile.core.donation.DonationImg;
import com.fsmile.core.donation.DonationModel;
import com.fsmile.core.donation.DonationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.app.donation.persistence.dto
 * Author revouna
 * Date 04/05/2023
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Donation implements DonationModel {
    protected String donationId;
    protected String donationName;
    protected DonationStatus status;
    protected boolean isAnonymous;
    protected List<DonationImg> donationImgs;
    protected Date dateCreated;

    @Override
    public String donationId() {
        return donationId;
    }

    @Override
    public String donationName() {
        return donationName;
    }

    @Override
    public DonationStatus status() {
        return status;
    }

    @Override
    public boolean isAnonymous() {
        return isAnonymous;
    }

    @Override
    public List<DonationImg> donationImgs() {
        return donationImgs;
    }

    @Override
    public Date dateCreated() {
        return dateCreated;
    }


}
