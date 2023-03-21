package com.fsmile.admin.domain.user;

import com.fsmile.core.domain.user.api.User;
import com.fsmile.core.domain.user.api.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(path = "signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        userApi.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
