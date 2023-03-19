package com.fsmile.admin.domain.donation.persistence;

import com.fsmile.admin.domain.user.persistence.UserEntity;
import com.fsmile.core.domain.donation.api.DonationBeneficiary;
import com.fsmile.core.domain.donation.api.DonationCategory;
import com.fsmile.core.domain.donation.api.DonationImg;
import com.fsmile.core.domain.donation.api.DonationStatus;
import com.fsmile.core.domain.user.api.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation.persistence
 * Author revouna
 * Date 07/03/2023
 */

@Entity
@Table(name = "DONATION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonationEntity {

    @Id
    private String donationId;
    private String donationName;
    @ManyToOne
    private UserEntity donor;
    @ManyToOne
    private DonationBeneficiaryEntity beneficiary;
    private DonationStatus status;
    private boolean isAnonymous;
    @OneToMany
    private List<DonationImgEntity> donationImgs;
    @ManyToOne
    private DonationCategoryEntity category;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;
    @UpdateTimestamp
    @Column(updatable = false)
    private Date updatedDate;
}
