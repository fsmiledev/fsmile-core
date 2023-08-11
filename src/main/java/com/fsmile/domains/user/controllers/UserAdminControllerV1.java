package com.fsmile.domains.user.controllers;

import com.fsmile.core.user.ResetPassword;
import com.fsmile.core.user.UserService;
import com.fsmile.domains.user.models.User;
import com.fsmile.core.user.UserAuth;
import com.fsmile.domains.user.models.UserPassword;
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
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserAdminControllerV1 {

    private final UserService userService;

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody UserAuth user) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(user));
    }
    @PostMapping(path = "signup")
    public ResponseEntity<?> signup(@RequestBody UserPassword user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(user));
    }

    @PutMapping(path = "editProfile")
    public ResponseEntity<?> editProfile(@RequestBody User user) throws Exception {
        userService.updateProfile(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "getUserByEmail/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(email));
    }

    @PutMapping(path = "resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword password) throws Exception {
        userService.resetUserPassword(password);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
