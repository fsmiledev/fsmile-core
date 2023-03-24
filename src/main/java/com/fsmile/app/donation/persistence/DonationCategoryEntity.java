package com.fsmile.app.donation.persistence;

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
@Table(name = "DONATION_CATEGORY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonationCategoryEntity {
    @Id
    String categoryId;
    String categoryName;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;
    @UpdateTimestamp
    @Column(updatable = false)
    private Date updatedDate;

    public DonationCategoryEntity(String categoryId) {
        this.categoryId = categoryId;
    }
}
