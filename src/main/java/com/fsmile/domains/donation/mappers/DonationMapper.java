package com.fsmile.domains.donation.mappers;

import com.fsmile.core.donation.DonationBeneficiary;
import com.fsmile.core.donation.DonationCategory;
import com.fsmile.core.donation.DonationImg;
import com.fsmile.core.donation.DonationModel;
import com.fsmile.core.language.LanguageTextService;
import com.fsmile.core.language.ParentAttribute;
import com.fsmile.domains.donation.entities.DonationBeneficiaryEntity;
import com.fsmile.domains.donation.entities.DonationCategoryEntity;
import com.fsmile.domains.donation.entities.DonationEntity;
import com.fsmile.domains.donation.models.DonationFull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author raphael
 * @project fsmile-api
 * @package com.fsmile.app.donation.mapper
 * @date 5/18/23 : 2:14 PM
 */

@Component
@RequiredArgsConstructor
public class DonationMapper {

    private final LanguageTextService languageTextService;

    public DonationModel donation(DonationEntity donation) {
        return new DonationFull(
                donation.getDonationId(),
                languageTextService.translateText(donation.getDonationId(), ParentAttribute.DONATION_NAME, Locale.getDefault()),
                donation.getStatus(),
                donation.isAnonymous(),
                donation.getDonationImgs().stream().map(img -> DonationImg.builder().imgId(img.getImgId()).imgUrl(img.getImgUrl()).build()).toList(),
                donation.getCreatedDate(),
                DonationCategory.builder().categoryId(donation.getCategory().getCategoryId())
                        .categoryName(languageTextService.translateText(donation.getCategory().getCategoryId(), ParentAttribute.CATEGORY_NAME, Locale.getDefault())).build(),
                languageTextService.findAllByParentId(donation.getDonationId(), ParentAttribute.DONATION_NAME));
    }


    public DonationBeneficiary beneficiary(DonationBeneficiaryEntity entity) {
        return DonationBeneficiary.builder()
                .beneficiaryId(entity.getBeneficiaryId())
                .name(entity.getName())
                .email(entity.getEmail())
                .imgUrl(entity.getImgUrl())
                .description(entity.getDescription())
                .phoneNumber1(entity.getPhoneNumber1())
                .phoneNumber2(entity.getPhoneNumber2())
                .phoneNumber3(entity.getPhoneNumber3())
                .addressLine1(entity.getAddressLine1())
                .addressLine2(entity.getAddressLine2())
                .addressLine3(entity.getAddressLine3())
                .rib(entity.getRib())
                .build();
    }


    public DonationCategory category(DonationCategoryEntity entity) {
        return DonationCategory.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(languageTextService.translateText(entity.getCategoryId(), ParentAttribute.CATEGORY_NAME, Locale.getDefault()))
                .slogan(languageTextService.translateText(entity.getCategoryId(), ParentAttribute.CATEGORY_SLOGAN, Locale.getDefault()))
                .categoryNames(languageTextService.findAllByParentId(entity.getCategoryId(), ParentAttribute.CATEGORY_NAME))
                .slogans(languageTextService.findAllByParentId(entity.getCategoryId(), ParentAttribute.CATEGORY_SLOGAN))
                .build();
    }

}
