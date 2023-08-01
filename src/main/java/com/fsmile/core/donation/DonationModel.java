package com.fsmile.core.donation;

import java.util.Date;
import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation
 * Author revouna
 * Date 21/02/2023
 */
public interface DonationModel {

    String donationId();
    String donationName();
    //User donor,
    //DonationBeneficiary beneficiary,
    DonationStatus status();
    Boolean isAnonymous();
    List<DonationImg> donationImgs();
    Date dateCreated();
}



