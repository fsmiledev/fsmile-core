package com.fsmile.admin.domain.donation.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.donation.persistence
 * Author revouna
 * Date 07/03/2023
 */

@Entity
@Table(name = "DONATION_BENEFICIARY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonationBeneficiaryEntity {

    @Id
    String beneficiaryId;
    String name;
    String description;
    String email;
    String rib;
    Long phoneNumber1;
    Long phoneNumber2;
    Long phoneNumber3;

    String addressLine1;
    String addressLine2;
    String addressLine3;

    String imgUrl;
    String doc;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;
    @UpdateTimestamp
    @Column(updatable = false)
    private Date updatedDate;

}
