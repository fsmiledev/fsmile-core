package com.fsmile.utils;

import com.fsmile.admin.domain.donation.persistence.DonationEntity;
import com.fsmile.core.domain.donation.api.Donation;
import com.fsmile.core.domain.donation.api.DonationCategory;
import com.fsmile.core.domain.donation.api.DonationImg;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.utils
 * @date 2/19/23 : 7:21 PM
 */
public class MapUtils {

    public static Page<Donation> mapDonationPageToDtoPage(CompletableFuture<Page<DonationEntity>> donations) throws Exception {
        return donations.get().map(donation -> Donation.builder()
                .donationId(donation.getDonationId())
                .status(donation.getStatus())
                .isAnonymous(donation.isAnonymous())
                .dateCreated(donation.getCreatedDate())
                .category(DonationCategory.builder().categoryId(donation.getCategory().getCategoryId()).categoryName(donation.getCategory().getCategoryName()).build())
                .donationImgs(donation.getDonationImgs().stream().map(img -> DonationImg.builder().imgId(img.getImgId()).imgUrl(img.getImgUrl()).build()).toList())
                .build());
    }
}
