package com.fsmile.app.user.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.admin.domain.user.persistence
 * @date 3/23/23 : 3:52 AM
 */

@Entity
@Table(name = "USER_MORE_INFOS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserMoreInfosEntity {

    @Id
    @Column(name = "USER_MORE_INFOS_ID")
    private String userMoreInfosId;
    @Column(name = "IMAGE_COVER_URL")
    private String imageCoverUrl;
    @Column(name = "PHONE_NUMBER")
    private long phoneNumber;
    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
