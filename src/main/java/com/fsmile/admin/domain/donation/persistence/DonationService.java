package com.fsmile.admin.domain.donation.persistence;

import com.fsmile.admin.domain.user.persistence.UserEntity;
import com.fsmile.core.domain.donation.api.Donation;
import com.fsmile.core.domain.donation.api.DonationImg;
import com.fsmile.core.domain.donation.api.DonationRepository;
import com.fsmile.core.domain.donation.api.DonationStatus;
import com.fsmile.core.domain.user.api.User;
import com.fsmile.utils.MapUtils;
import com.fsmile.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation.persistence
 * Author revouna
 * Date 07/03/2023
 */

@Service
@AllArgsConstructor
public class DonationService implements DonationRepository {

    private final DonationJpaRepository donationRepository;

    @Override
    public String create(Donation donation) {
        String id = StringUtils.uuid();
      /*  List<DonationImgEntity> images = donation.donationImgs().stream().map(img ->
                new DonationImgEntity(img.imgId(), img.donationId(), img.imgUrl(), null, null)).toList();*/

        DonationEntity s = DonationEntity.builder()
                .donationId(id)
           //     .donationImgs(images)
                .isAnonymous(donation.isAnonymous())
                .status(DonationStatus.SAVED)
                .donor(new UserEntity(donation.donor().userId()))
                .category(new DonationCategoryEntity(donation.category().categoryId()))
                .build();
        donationRepository.save(s);
        return id;
    }

    @Override
    public String update(Donation donation) {
        return null;
    }

    @Override
    public String delete(String s) {
        return null;
    }

    @Override
    public Donation find(String s) {
        return null;
    }

    @Override
    public Page<Donation> findAllBy(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<DonationEntity>> donations = donationRepository.findAllBy(pageable);
        return MapUtils.mapEntityPageIntoDtoPage(donations, Donation.class);
    }
}
