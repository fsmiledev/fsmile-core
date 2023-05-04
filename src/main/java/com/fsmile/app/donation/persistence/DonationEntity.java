package com.fsmile.app.donation.persistence;

import com.fsmile.app.user.persistence.UserEntity;
import com.fsmile.core.donation.api.DonationStatus;
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
    @Enumerated(EnumType.STRING)
    private DonationStatus status;
    private boolean isAnonymous;
    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL)
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
