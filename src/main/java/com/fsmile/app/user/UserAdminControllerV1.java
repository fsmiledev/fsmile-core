package com.fsmile.app.user;

import com.fsmile.core.user.api.ResetPassword;
import com.fsmile.core.user.api.User;
import com.fsmile.core.user.api.UserApi;
import com.fsmile.core.user.api.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.admin.domain.user
 * @date 2/19/23 : 6:51 PM
 */

@RestController
@RequestMapping("api/user/v0")
@RequiredArgsConstructor
public class UserAdminControllerV1 {

    private final UserApi  userApi;

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody UserAuth user) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userApi.login(user));
    }
    @PostMapping(path = "signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userApi.createUser(user));
    }

    @PutMapping(path = "editProfile")
    public ResponseEntity<?> editProfile(@RequestBody User user) throws Exception {
        userApi.updateProfile(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userApi.getUserByEmail(email));
    }

    @PutMapping(path = "resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword password) throws Exception {
        userApi.resetUserPassword(password);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
