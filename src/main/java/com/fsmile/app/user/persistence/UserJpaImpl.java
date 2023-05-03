package com.fsmile.app.user.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsmile.core.user.api.*;
import com.fsmile.utils.BundleUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
    @Value("${auth.realm.name}")
    private String realm;
    @Value("${auth.realm.client-id}")
    private String clientId;
    @Value("${auth.server.token}")
    private String authUrl;
    private final Keycloak instance;
    private final UserJpaRepository userJpaRepository;


    @Override
    public UserToken login(UserAuth userAuth) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> userObject = new LinkedMultiValueMap<>();
        userObject.add("client_id", clientId);
        userObject.add("grant_type", userAuth.grandType());
        Assert.notNull(userAuth.grandType(), BundleUtils.message("grand_type_required"));
        if (userAuth.grandType().equals("password")) {
            Assert.notNull(userAuth.username(), BundleUtils.message("username_required"));
            Assert.notNull(userAuth.password(),  BundleUtils.message("password_required"));
            userObject.add("username", userAuth.username());
            userObject.add("password", userAuth.password());
        } else if (userAuth.grandType().equals("refresh_token")) {
            Assert.notNull(userAuth.refreshToken(),  BundleUtils.message("refresh_token_required"));
            userObject.add("refresh_token", userAuth.refreshToken());
        } else throw new Exception( BundleUtils.message("invalid_grand_type"));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(userObject, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(authUrl, HttpMethod.POST, requestEntity, String.class);
            return objectMapper.readValue(responseEntity.getBody(), UserToken.class);
        } catch (Exception e) {
            String message = e.getMessage().contains("401 Unauthorized") ?
                    BundleUtils.message("bad_credentials") :
                    BundleUtils.message("error_during_connection") + ". " + BundleUtils.message("contact_administrator");
            throw new Exception(message);
        }
    }

    @Override
    public String createUser(UserModel user) {
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
    public void updateProfile(UserModel user) throws Exception {
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
        UserModel user = getUserByEmail(resetPassword.userEmail());
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
    public UserModel getUserByEmail(String email) throws Exception {
        List<UserRepresentation> list = instance.realm(realm).users().searchByEmail(email, true);
        if (list.isEmpty()) throw new Exception("User not found");
        UserRepresentation userR = list.get(0);
        System.out.println(userR.getRealmRoles());
        List<String> role = new ArrayList<>();
        return new UserRole(userR.getId(), userR.getUsername(),userR.getLastName(), userR.getEmail(), role);
    }

    @Override
    public User getUserByDonationId(String donationId) throws Exception {
        return null;
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
    public Page<UserModel> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<UserEntity>> userEntities = userJpaRepository.findAllBy(pageable);
        return null;
    }

    @Override
    public void createUserSetting(UserSetting userSetting) {

    }

    @Override
    public void editUserSetting(UserSetting userSetting) {

    }

    @Override
    public void createUserConnection(UserConnection connection) {

    }
}
