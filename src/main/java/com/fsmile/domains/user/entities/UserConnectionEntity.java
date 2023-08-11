package com.fsmile.domains.user.entities;

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
 * @package com.fsmile.domains.user.entities
 * @date 3/23/23 : 3:53 AM
 */

@Entity
@Table(name = "USER_CONNECTION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserConnectionEntity {
    @Id @Column(name = "USER_CONNECTION_ID")
    private String userConnectionId;
    @Column(name = "COUNTRY")
    String country;
    @Column(name = "USER_AGENT")
    String userAgent;
    @Column(name = "IP_ADDRESS")
    String ipAddress;
    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}
