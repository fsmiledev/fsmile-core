package com.fsmile.app.donation;

import com.fsmile.app.donation.entities.DonationBeneficiaryEntity;
import com.fsmile.app.donation.entities.DonationCategoryEntity;
import com.fsmile.app.donation.entities.DonationEntity;
import com.fsmile.app.donation.entities.DonationImgEntity;
import com.fsmile.app.donation.mapper.DonationMapper;
import com.fsmile.app.donation.models.DonationAddModel;
import com.fsmile.app.donation.repositories.DonationBeneficiaryEntityRepository;
import com.fsmile.app.donation.repositories.DonationCategoryEntityRepository;
import com.fsmile.app.donation.repositories.DonationImgEntityRepository;
import com.fsmile.app.donation.repositories.DonationJpaRepository;
import com.fsmile.app.user.persistence.UserEntity;
import com.fsmile.core.donation.api.*;
import com.fsmile.core.language.api.LanguageTextService;
import com.fsmile.utils.MapUtils;
import com.fsmile.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
public class DonationCoreImpl implements DonationCore {

    private final DonationJpaRepository donationRepository;
    private final DonationBeneficiaryEntityRepository donationBeneficiaryRepository;
    private final DonationCategoryEntityRepository donationCategoryRepository;
    private final DonationImgEntityRepository donationImgRepository;

    private final LanguageTextService languageTextService;

    @Override
    public void addDonation(DonationModel donation) {
        DonationAddModel add = (DonationAddModel) donation;
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
        donation = new DonationAddModel();
        DonationAddModel obj = (DonationAddModel) donation;
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
        DonationEntity donation = donationRepository.getReferenceById(donationId);
        return MapUtils.mapDonationEntityToDonationDto(donation);
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
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
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
    public String addDonationBeneficiary(DonationBeneficiary beneficiary) {
        Assert.notNull(beneficiary.name(), "Name can be null");
        String id = beneficiary.beneficiaryId() == null ? StringUtils.uuid(): beneficiary.beneficiaryId();
        DonationBeneficiaryEntity entity = DonationBeneficiaryEntity.builder()
                .beneficiaryId(id)
                .addressLine1(beneficiary.addressLine1())
                .addressLine3(beneficiary.addressLine2())
                .addressLine3(beneficiary.addressLine3())
                .doc(beneficiary.doc())
                .email(beneficiary.email())
                .imgUrl(beneficiary.imgUrl())
                .name(beneficiary.name())
                .description(beneficiary.description())
                .phoneNumber1(beneficiary.phoneNumber1())
                .phoneNumber2(beneficiary.phoneNumber2())
                .phoneNumber3(beneficiary.phoneNumber3())
                .rib(beneficiary.rib())
                .build();
        donationBeneficiaryRepository.save(entity);
        return id;
    }

    @Override
    public DonationBeneficiary getDonationBeneficiary(String beneficiaryId) {
        DonationBeneficiaryEntity entity = donationBeneficiaryRepository.getReferenceById(beneficiaryId);
        return DonationMapper.beneficiary(entity);
    }


    @Override
    public Page<DonationBeneficiary> getAllDonationBeneficiaries(int page, int size) throws Exception{
        CompletableFuture<Page<DonationBeneficiaryEntity>> beneficiaryPage = donationBeneficiaryRepository.findAllByOrderByNameAsc(PageRequest.of(page, size));
        return DonationMapper.beneficiaries().map(beneficiaryPage);
    }
}
