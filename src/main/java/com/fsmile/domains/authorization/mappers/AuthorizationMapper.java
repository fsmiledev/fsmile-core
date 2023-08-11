package com.fsmile.domains.authorization.mappers;

import com.fsmile.core.authorization.Client;
import com.fsmile.core.authorization.Group;
import com.fsmile.core.authorization.Role;
import com.fsmile.domains.authorization.AuthorizationServiceImpl;
import com.fsmile.domains.authorization.entities.ClientEntity;
import com.fsmile.domains.authorization.entities.GroupEntity;
import com.fsmile.domains.authorization.entities.RoleEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Project fsmile-core
 * Package com.fsmile.domains.authorization.mappers
 * Author revouna
 * Date 09/08/2023
 */

@Component
public class AuthorizationMapper {

    public Group mapFromGroupEntity(@NotNull GroupEntity groupEntity) {
        return Group.builder()
                .groupId(groupEntity.getGroupId())
                .code(groupEntity.getCode())
                .name(groupEntity.getName())
                .roles(groupEntity.getRoleEntities().stream().map(this::mapFromRoleEntity).toList())
                .build();
    }

    public Role mapFromRoleEntity(RoleEntity role) {
        return Role.builder()
                .roleId(role.getRoleId())
                .code(role.getCode())
                .name(role.getName())
                .build();
    }

    public Client mapFromClientEntity(ClientEntity client) {
        return Client.builder()
                .clientId(client.getClientId())
                .clientName(client.getClientName())
                .bearerOnly(client.isBearerOnly())
                .publicClient(client.isPublicClient())
                .protocol(client.getProtocol())
                .accessTokenTimeout(client.getAccessTokenTimeout())
                .refreshTokenTimeout(client.getRefreshTokenTimeout())
                .rootUrl(client.getRootUrl())
                .baseUrl(client.getBaseUrl())
                .date(client.getDate())
                .build();
    }
}
