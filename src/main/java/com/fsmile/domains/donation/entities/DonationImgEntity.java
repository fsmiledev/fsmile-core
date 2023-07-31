package com.fsmile.domains.donation.entities;

import jakarta.persistence.*;
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
@Table(name = "DONATION_IMAGE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonationImgEntity {

    @Id
    private String imgId;
    private String imgUrl;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;
    @UpdateTimestamp
    @Column(updatable = false)
    private Date updatedDate;
    @ManyToOne
    private DonationEntity donation;
}
