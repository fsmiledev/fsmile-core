package com.fsmile.core.user;

/**
 * Project trunk
 * Package com.fsmile.core.domain.user.api
 * Author revouna
 * Date 22/03/2023
 */
public record ResetPassword(

        String userEmail,
        String currentPassword,
        String newPassword
) {
}
