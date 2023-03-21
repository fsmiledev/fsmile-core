package com.fsmile.admin.domain.user.persistence;

import com.fsmile.core.domain.user.api.User;
import com.fsmile.core.domain.user.api.UserRepository;
import com.fsmile.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
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
    public Page<User> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<UserEntity>> userEntities = userJpaRepository.findAllBy(pageable);
        return null;
    }
}
