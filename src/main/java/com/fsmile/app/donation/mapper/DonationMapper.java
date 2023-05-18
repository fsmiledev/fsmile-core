package com.fsmile.app.donation.mapper;

import com.fsmile.app.donation.entities.DonationBeneficiaryEntity;
import com.fsmile.app.donation.entities.DonationEntity;
import com.fsmile.core.donation.api.DonationBeneficiary;
import com.fsmile.utils.MapAsyncEntityPageToDtoPage;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletableFuture;

/**
 * @author raphael
 * @project fsmile-api
 * @package com.fsmile.app.donation.mapper
 * @date 5/18/23 : 2:14 PM
 */
public class DonationMapper {

    public static MapAsyncEntityPageToDtoPage<DonationBeneficiary, DonationBeneficiaryEntity> beneficiaries() {
        return (b) -> b.get().map(DonationMapper::beneficiary);

    }

    public static DonationBeneficiary beneficiary(DonationBeneficiaryEntity entity){
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

}
