package com.fsmile.domains.user.entities;

import com.fsmile.core.user.Language;
import com.fsmile.core.user.Theme;
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
@Table(name = "USER_SETTING")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSettingEntity {

    @Id
    @Column(name = "SETTING_ID")
    private String settingId;
    @Column(name = "DEFAULT_LANGUAGE")
    private Language defaultLanguage;
    @Column(name = "DEFAULT_THEME")
    private Theme defaultTheme;
    @Column(name = "ALLOW_COOKIES")
    private boolean allowCookies;
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
