package com.fsmile.core.domain.user.api;

import com.fsmile.core.domain.common.GenericApi;
import com.fsmile.core.domain.common.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author raphael
 * @project fsmile
 * @package com.fsmile.core.domain.user.api
 * @date 2/18/23 : 6:46 PM
 */

public interface UserApi {

    void createUser(User user);

    Page<User> findAllUsers(int page, int size);


}
