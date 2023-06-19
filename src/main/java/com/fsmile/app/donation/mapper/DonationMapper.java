package com.fsmile.app.donation.mapper;

import com.fsmile.app.donation.entities.DonationBeneficiaryEntity;
import com.fsmile.app.donation.entities.DonationCategoryEntity;
import com.fsmile.app.donation.entities.DonationEntity;
import com.fsmile.app.donation.models.DonationFull;
import com.fsmile.core.donation.api.DonationBeneficiary;
import com.fsmile.core.donation.api.DonationCategory;
import com.fsmile.core.donation.api.DonationImg;
import com.fsmile.core.donation.api.DonationModel;
import com.fsmile.core.language.api.LanguageTextService;
import com.fsmile.core.language.api.ParentAttribute;
import com.fsmile.utils.MapAsyncEntityPageToDtoPage;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile-api
 * @package com.fsmile.app.donation.mapper
 * @date 5/18/23 : 2:14 PM
 */
public class DonationMapper {

    private static LanguageTextService languageTextService;

    public DonationMapper(LanguageTextService languageTextService) {
        DonationMapper.languageTextService = languageTextService;
    }

    public static Page<DonationModel> mapDonationPageToDtoPage(CompletableFuture<Page<DonationEntity>> donations) throws Exception {
        return donations.get().map(DonationMapper::mapDonationEntityToDonationDto);
    }

    public static DonationModel mapDonationEntityToDonationDto(DonationEntity donation) {
        return new DonationFull(
                donation.getDonationId(),
                languageTextService.translateText(donation.getDonationId(), ParentAttribute.DONATION_NAME, Locale.getDefault()),
                donation.getStatus(),
                donation.isAnonymous(),
                donation.getDonationImgs().stream().map(img -> DonationImg.builder().imgId(img.getImgId()).imgUrl(img.getImgUrl()).build()).toList(),
                donation.getCreatedDate(),
                DonationCategory.builder().categoryId(donation.getCategory().getCategoryId())
                        .categoryName(languageTextService.translateText(donation.getCategory().getCategoryId(), ParentAttribute.CATEGORY_NAME, Locale.getDefault())).build(),
                languageTextService.getTextByParentId(donation.getDonationId(), ParentAttribute.DONATION_NAME));
    }

    public static MapAsyncEntityPageToDtoPage<DonationBeneficiary, DonationBeneficiaryEntity> beneficiaries() {
        return (b) -> b.get().map(DonationMapper::beneficiary);

    }

    public static DonationBeneficiary beneficiary(DonationBeneficiaryEntity entity) {
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

    public static List<DonationCategory> donationCategories(List<DonationCategoryEntity> entities) {
        return entities.stream().map(DonationMapper::category).toList();
    }

    public static DonationCategory category(DonationCategoryEntity entity) {
        return DonationCategory.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(languageTextService.translateText(entity.getCategoryId(), ParentAttribute.CATEGORY_NAME, Locale.getDefault()))
                .slogan(languageTextService.translateText(entity.getCategoryId(), ParentAttribute.CATEGORY_SLOGAN, Locale.getDefault()))
                .categoryNames(languageTextService.getTextByParentId(entity.getCategoryId(), ParentAttribute.CATEGORY_NAME))
                .slogans(languageTextService.getTextByParentId(entity.getCategoryId(), ParentAttribute.CATEGORY_SLOGAN))
                .build();
    }

}
