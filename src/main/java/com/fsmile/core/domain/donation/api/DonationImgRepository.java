package com.fsmile.core.domain.donation.api;

import com.fsmile.core.domain.common.GenericRepository;

import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.core.domain.donation.api
 * Author revouna
 * Date 21/02/2023
 */
public interface DonationImgRepository extends GenericRepository<DonationImg, String> {
    void create(List<DonationImg> donationImgs);
}
