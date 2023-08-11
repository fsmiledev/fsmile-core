package com.fsmile.domains.authorization;

import com.fsmile.core.authorization.AuthorizationService;
import com.fsmile.core.authorization.Client;
import com.fsmile.core.authorization.Group;
import com.fsmile.core.authorization.Role;
import com.fsmile.domains.authorization.entities.*;
import com.fsmile.domains.authorization.mappers.AuthorizationMapper;
import com.fsmile.domains.authorization.repositories.ClientRepository;
import com.fsmile.domains.authorization.repositories.GroupRepository;
import com.fsmile.domains.authorization.repositories.RoleRepository;
import com.fsmile.domains.authorization.repositories.UserGroupMappingRepository;
import com.fsmile.domains.user.entities.UserEntity;
import com.fsmile.shared.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.domains.authorization
 * Author revouna
 * Date 09/08/2023
 */

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationMapper authorizationMapper;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    private final ClientRepository clientRepository;
    private final UserGroupMappingRepository userGroupMappingRepository;

    @Override
    public void addGroup(Group group) {
        String id = group.groupId() == null ? StringUtils.uuid() : group.groupId();
        GroupEntity groupEntity = GroupEntity.builder()
                .groupId(id)
                .code(group.code())
                .name(group.name())
                .build();
        groupRepository.save(groupEntity);
    }

    @Override
    public void addRole(Role role) {
        String id = role.roleId() == null ? StringUtils.uuid() : role.roleId();
        RoleEntity roleEntity = RoleEntity.builder()
                .roleId(id)
                .groupEntity(new GroupEntity(role.groupId()))
                .code(role.code())
                .name(role.name())
                .build();
        roleRepository.save(roleEntity);
    }

    @Override
    public void addUserToGroup(String userId, String groupId) {
        UserGroupMappingKey key = UserGroupMappingKey.builder().userId(userId).groupId(groupId).build();
        UserGroupMapping userGroupMapping = UserGroupMapping.builder().key(key).user(new UserEntity(userId)).groupEntity(new GroupEntity(groupId)).build();
        userGroupMappingRepository.save(userGroupMapping);
    }

    @Override
    public void removeUserToGroup(String userId, String groupId) {
        UserGroupMappingKey key = UserGroupMappingKey.builder().userId(userId).groupId(groupId).build();
        userGroupMappingRepository.deleteById(key);
    }

    @Override
    public List<Group> getGroupByUser(String userId) {
        List<GroupEntity> groupEntities = groupRepository.findByUserId(userId);
        return groupEntities.stream().map(authorizationMapper::mapFromGroupEntity).toList();
    }

    @Override
    public List<Group> getAllGroup() {
        return groupRepository.findAll().stream().map(authorizationMapper::mapFromGroupEntity).toList();
    }

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll().stream().map(authorizationMapper::mapFromClientEntity).toList();
    }

    @Override
    public Client getClientById(String clientId) {
        ClientEntity client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Unable to find client with id " + clientId));
        return authorizationMapper.mapFromClientEntity(client);
    }

    @Override
    public String saveClient(Client client) {
        String id = client.clientId() == null ? StringUtils.uuid() : client.clientId();
        if (client.accessTokenTimeout() >= client.refreshTokenTimeout())
            throw new RuntimeException("access token timeout can not be equals or bigger than refresh token timeout");
        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(id)
                .clientName(client.clientName())
                .bearerOnly(client.bearerOnly())
                .publicClient(client.publicClient())
                .protocol(client.protocol())
                .accessTokenTimeout(client.accessTokenTimeout())
                .refreshTokenTimeout(client.refreshTokenTimeout())
                .rootUrl(client.rootUrl())
                .baseUrl(client.baseUrl())
                .build();
        clientRepository.save(clientEntity);
        return id;
    }

    @Override
    public void deleteClient(String clientId) {
        clientRepository.deleteById(clientId);
    }
}
