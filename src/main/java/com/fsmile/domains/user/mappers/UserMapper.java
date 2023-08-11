package com.fsmile.domains.user.mappers;

import com.fsmile.domains.authorization.entities.GroupEntity;
import com.fsmile.domains.authorization.mappers.AuthorizationMapper;
import com.fsmile.domains.authorization.repositories.GroupRepository;
import com.fsmile.domains.user.entities.UserEntity;
import com.fsmile.domains.user.models.UserGroups;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Project fsmile-core
 * Package com.fsmile.domains.user.mappers
 * Author revouna
 * Date 11/08/2023
 */

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final GroupRepository groupRepository;
    public UserGroups mapFromUserEntity(UserEntity u) {
        return new  UserGroups(u.getUserId(), u.getUsername(), u.getLastName(), u.getEmail(), u.getFirstName(), u.getCreatedDate(),
                groupRepository.findByUserId(u.getUserId()).stream().map(GroupEntity::getName).toList());
    }
}
