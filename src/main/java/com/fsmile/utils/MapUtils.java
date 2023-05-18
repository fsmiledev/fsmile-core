package com.fsmile.utils;

import com.fsmile.app.donation.entities.DonationEntity;
import com.fsmile.app.donation.models.DonationFull;
import com.fsmile.core.donation.api.DonationCategory;
import com.fsmile.core.donation.api.DonationImg;
import com.fsmile.core.donation.api.DonationModel;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.utils
 * @date 2/19/23 : 7:21 PM
 */
public class MapUtils {

    public static Page<DonationModel> mapDonationPageToDtoPage(CompletableFuture<Page<DonationEntity>> donations) throws Exception {
        return donations.get().map(donation -> new DonationFull(
                donation.getDonationId(),
                donation.getDonationName(),
                donation.getStatus(),
                donation.isAnonymous(),
                donation.getDonationImgs().stream().map(img -> DonationImg.builder().imgId(img.getImgId()).imgUrl(img.getImgUrl()).build()).toList(),
                donation.getCreatedDate(),
                DonationCategory.builder().categoryId(donation.getCategory().getCategoryId()).categoryName(donation.getCategory().getCategoryName()).build()
        ));
    }
}
