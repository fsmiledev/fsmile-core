package com.fsmile.app.donation.persistence;

import com.fsmile.app.donation.persistence.dto.AddDonation;
import com.fsmile.app.user.persistence.UserEntity;
import com.fsmile.core.donation.api.*;
import com.fsmile.utils.MapUtils;
import com.fsmile.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class DonationJpaImpl implements DonationRepository {

    private final DonationJpaRepository donationRepository;
    private final DonationBeneficiaryEntityRepository donationBeneficiaryRepository;
    private final DonationCategoryEntityRepository donationCategoryRepository;
    private final DonationImgEntityRepository donationImgRepository;

    @Override
    public void addDonation(DonationModel donation) {
        AddDonation add = (AddDonation) donation;
        String donationId = StringUtils.uuid();
        DonationEntity s = DonationEntity.builder()
                .donationId(donationId)
                .donationName(add.donationName())
                .isAnonymous(add.isAnonymous())
                .status(DonationStatus.SAVED)
                .donor(add.isAnonymous() ? null : new UserEntity(add.donorId()))
                .category(new DonationCategoryEntity(add.categoryId()))
                .build();
        donationRepository.save(s);
        List<DonationImgEntity> donationImgEntities = add.donationImgs().stream().map(img -> DonationImgEntity
                .builder()
                .imgId(StringUtils.uuid())
                .donation(s)
                .imgUrl(img.imgUrl())
                .build()).toList();
        donationImgRepository.saveAll(donationImgEntities);
    }


    @Override
    public void deleteDonationImg(String imgId) {
        donationImgRepository.deleteById(imgId);
    }

    @Override
    public void deleteDonation(String donationId) {
        donationRepository.deleteById(donationId);
    }

    @Override
    public void editDonation(DonationModel donation) {
        donation = new AddDonation();
        AddDonation obj = (AddDonation) donation;
        DonationEntity donationEntity = donationRepository.getReferenceById(obj.donationId());
        if (obj.categoryId() != null){
            donationEntity.setCategory(new DonationCategoryEntity(obj.categoryId()));
        }
        donationEntity.setDonationName(donation.donationName());
        donationRepository.save(donationEntity);
        List<DonationImgEntity> donationImgEntities = donation.donationImgs().stream().map(img -> DonationImgEntity
                .builder()
                .imgId(StringUtils.uuid())
                .donation(donationEntity)
                .imgUrl(img.imgUrl())
                .build()).toList();
        donationImgRepository.saveAll(donationImgEntities);
    }

    @Override
    public DonationModel getDonation(String donationId) {
        return null;
    }

    @Override
    public void validateDonation(String donationId) {
        DonationEntity donationEntity = donationRepository.getReferenceById(donationId);
        donationEntity.setStatus(DonationStatus.VALIDATE);
        donationRepository.save(donationEntity);
    }

    @Override
    public void rejectDonation(String donationId) {
        DonationEntity donationEntity = donationRepository.getReferenceById(donationId);
        donationEntity.setStatus(DonationStatus.REJECT);
        donationRepository.save(donationEntity);
    }

    @Override
    public void giveDonations(List<String> donationIds, String beneficiaryId) {
        List<DonationEntity> donationEntities = donationIds.stream().map(donationId -> {
            DonationEntity donationEntity =  donationRepository.getReferenceById(donationId);
            donationEntity.setStatus(DonationStatus.GIVE);
            donationEntity.setBeneficiary(new DonationBeneficiaryEntity(beneficiaryId));
            return donationEntity;
        }).toList();
        donationRepository.saveAll(donationEntities);
    }

    @Override
    public void confirmDonationGive(List<String> donationIds) {
        List<DonationEntity> donationEntities = donationIds.stream().map(donationId -> {
            DonationEntity donationEntity =  donationRepository.getReferenceById(donationId);
            donationEntity.setStatus(DonationStatus.RECEIVED);
            return donationEntity;
        }).toList();
        donationRepository.saveAll(donationEntities);
    }

    @Override
    public String addDonationCategory(DonationCategory category) {
        String categoryId = StringUtils.uuid();
        DonationCategoryEntity categoryEntity = DonationCategoryEntity.builder()
                .categoryId(categoryId)
                .categoryName(category.categoryName())
                .build();
        donationCategoryRepository.save(categoryEntity);
        return categoryId;
    }

    @Override
    public void editDonationCategory(DonationCategory category) {
        DonationCategoryEntity categoryEntity = donationCategoryRepository.getReferenceById(category.categoryId());
        categoryEntity.setCategoryName(category.categoryName());
        donationCategoryRepository.save(categoryEntity);
    }

    @Override
    public List<DonationCategory> findAllCategories() {
        return donationCategoryRepository.findAll().stream().map(category -> DonationCategory.builder()
                .categoryId(category.categoryId)
                .categoryName(category.categoryName)
                .build()).toList();
    }

    @Override
    public Page<DonationModel> findAllDonation(int page, int size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<DonationEntity>> donations = donationRepository.findAllBy(pageable);
        return MapUtils.mapDonationPageToDtoPage(donations);
    }

    @Override
    public Page<DonationModel> findDonationsByStatus(int page, int size, DonationStatus status) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<DonationEntity>> donations = donationRepository.findDonationEntityByStatus(status, pageable);
        return MapUtils.mapDonationPageToDtoPage(donations);
    }

    @Override
    public DonationBeneficiary getDonationBeneficiary(String beneficiaryId) {
        return null;
    }

    @Override
    public Page<DonationBeneficiary> getAllDonationBeneficiaries(int page, int size) {
        return null;
    }
}
