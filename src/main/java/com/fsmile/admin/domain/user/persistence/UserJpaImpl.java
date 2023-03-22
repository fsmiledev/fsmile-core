package com.fsmile.admin.domain.user.persistence;

import com.fsmile.core.domain.user.api.*;
import com.fsmile.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Project trunk
 * Package com.fsmile.admin.domain.user.persistence
 * Author revouna
 * Date 21/03/2023
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserJpaImpl implements UserRepository {
    @Value("${realm}")
    private String realm;
    private final Keycloak instance;
    private final UserJpaRepository userJpaRepository;



    @Override
    public String createUser(User user) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.username());
        userRepresentation.setFirstName(user.firstName());
        userRepresentation.setLastName(user.lastName());
        userRepresentation.setEmail(user.email());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEnabled(true);
        userRepresentation.setRequiredActions(Collections.singletonList("Verify Email"));
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(user.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setUserLabel("label");
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        Response response = instance.realm(realm).users().create(userRepresentation);
        log.info("Response |  Status: {} | Status Info: {}", response.getStatus(), response.getStatusInfo());
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .firstName(user.firstName())
                .lastName(user.lastName())
                .username(user.username())
                .email(user.email())
                .build();
        userJpaRepository.save(userEntity);
        return userId;
    }

    @Override
    public void updateProfile(User user) throws Exception {
        UserRepresentation userRepresentation = new UserRepresentation();
        assert user.lastName() != null;
        assert user.firstName() != null;
        userRepresentation.setFirstName(user.firstName());
        userRepresentation.setLastName(user.lastName());
        instance.realm(realm).users().get(getUserByEmail(user.email()).userId()).update(userRepresentation);
        UserEntity userEntity = userJpaRepository.findByEmail(user.email());
        userEntity.setFirstName(user.firstName());
        userEntity.setLastName(user.lastName());
        userJpaRepository.save(userEntity);
    }

    @Override
    public void resetUserPassword(ResetPassword resetPassword) throws Exception {
        User user = getUserByEmail(resetPassword.userEmail());
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(resetPassword.newPassword());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setUserLabel("label");
        instance.realm(realm).users().get(user.userId()).resetPassword(credentialRepresentation);
    }

    @Override
    public void addUserMoresInfos(UserMoresInfos moresInfos) {

    }

    @Override
    public void updateUserMoresInfos(UserMoresInfos moresInfos) {

    }

    @Override
    public User getUserByEmail(String email) throws Exception {
        List<UserRepresentation> list = instance.realm(realm).users().searchByEmail(email, true);
        if (list.isEmpty()) throw new Exception("User not found");
        UserRepresentation userR = list.get(0);
        System.out.println(userR.getRealmRoles());
        return User.builder()
                .userId(userR.getId())
                .username(userR.getUsername())
                .email(userR.getEmail())
                .lastName(userR.getLastName())
                .firstName(userR.getFirstName())
                .roles(userR.getRealmRoles())
                .build();
    }

    @Override
    public UserMoresInfos getUserMoresInfos(String userEmail) {
        return null;
    }

    @Override
    public List<Role> getUserRoles(String userId) {
        return null;
    }

    @Override
    public Page<User> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<UserEntity>> userEntities = userJpaRepository.findAllBy(pageable);
        return null;
    }
}
