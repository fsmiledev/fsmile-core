package com.fsmile.admin.domain.donation.persistence;

import com.fsmile.admin.domain.user.persistence.UserEntity;
import com.fsmile.core.domain.donation.api.*;
import com.fsmile.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public String addDonation(Donation donation) {
        String id = StringUtils.uuid();
        DonationEntity s = DonationEntity.builder()
                .donationId(id)
                .isAnonymous(donation.isAnonymous())
                .status(DonationStatus.SAVED)
                .donor(new UserEntity(donation.donor().userId()))
                .category(new DonationCategoryEntity(donation.category().categoryId()))
                .build();
        donationRepository.save(s);
        return id;
    }


    @Override
    public void deleteDonationImg(String imgId) {

    }

    @Override
    public void deleteDonation(String donationId) {

    }

    @Override
    public void editDonation(Donation donation) {

    }

    @Override
    public void validateDonation(String donationId, DonationStatus validate) {

    }

    @Override
    public void rejectDonation(String donationId, DonationStatus reject) {

    }

    @Override
    public void giveDonations(List<Donation> donations, String beneficiaryId) {

    }

    @Override
    public void confirmDonationGive(List<Donation> donations, DonationStatus recept) {

    }

    @Override
    public String addDonationCategory(DonationCategory category) {
        return null;
    }

    @Override
    public void editDonationCategory(DonationCategory category) {

    }

    @Override
    public List<DonationCategory> findAllCategories() {
        return null;
    }

    @Override
    public Page<Donation> findAllDonation(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<DonationEntity>> donations = donationRepository.findAllBy(pageable);
        return donations.get().map(donation -> Donation.builder()
                .donationId(donation.getDonationId())
                .status(donation.getStatus())
                .isAnonymous(donation.isAnonymous())
                .dateCreated(donation.getCreatedDate())
                .category(DonationCategory.builder().categoryId(donation.getCategory().categoryId).categoryName(donation.getCategory().getCategoryName()).build())
                .donationImgs(donation.getDonationImgs().stream().map(img -> DonationImg.builder().imgId(img.getImgId()).imgUrl(img.getImgUrl()).build()).toList())
                .build());
    }

    @Override
    public Page<Donation> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception {
        return null;
    }
}
