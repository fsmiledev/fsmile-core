package com.fsmile.utils;

import com.fsmile.app.donation.entities.DonationEntity;
import com.fsmile.app.donation.entities.DonationImgEntity;
import com.fsmile.app.donation.models.DonationFull;
import com.fsmile.core.donation.api.DonationCategory;
import com.fsmile.core.donation.api.DonationImg;
import com.fsmile.core.donation.api.DonationModel;
import com.fsmile.core.language.api.LanguageTextService;
import com.fsmile.core.language.api.ParentAttribute;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.utils
 * @date 2/19/23 : 7:21 PM
 */


public class MapUtils {

    private static LanguageTextService languageTextService;

    public MapUtils(LanguageTextService languageTextService) {
        MapUtils.languageTextService = languageTextService;
    }

    public static Page<DonationModel> mapDonationPageToDtoPage(CompletableFuture<Page<DonationEntity>> donations) throws Exception {
        return donations.get().map(MapUtils::mapDonationEntityToDonationDto);
    }

    public static DonationModel mapDonationEntityToDonationDto(DonationEntity donation) {
        return  new DonationFull(
                donation.getDonationId(),
                languageTextService.translateText(donation.getDonationId(), ParentAttribute.DONATION_NAME, Locale.getDefault()),
                donation.getStatus(),
                donation.isAnonymous(),
                donation.getDonationImgs().stream().map(img -> DonationImg.builder().imgId(img.getImgId()).imgUrl(img.getImgUrl()).build()).toList(),
                donation.getCreatedDate(),
                DonationCategory.builder().categoryId(donation.getCategory().getCategoryId()).categoryName(donation.getCategory().getCategoryName()).build(),
                languageTextService.getTextByParentId(donation.getDonationId(), ParentAttribute.DONATION_NAME));
    }
}
