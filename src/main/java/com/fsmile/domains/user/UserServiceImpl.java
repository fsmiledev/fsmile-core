package com.fsmile.domains.user;

import com.fsmile.config.Security.CustomPasswordEncoder;
import com.fsmile.core.authorization.AuthorizationService;
import com.fsmile.core.authorization.Client;
import com.fsmile.core.user.*;
import com.fsmile.domains.user.entities.UserEntity;
import com.fsmile.domains.user.mappers.UserMapper;
import com.fsmile.domains.user.models.User;
import com.fsmile.domains.user.models.UserGroups;
import com.fsmile.domains.user.models.UserPassword;
import com.fsmile.domains.user.repositories.UserRepository;
import com.fsmile.shared.BundleUtils;
import com.fsmile.shared.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 This class provides implementation for the {@link UserService} interface.
 It interacts with the user data stored in a database using JPA and with a Keycloak server
 for authentication and authorization.
 @author Raphael Evouna
 @since 21/03/2023
 */

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorizationService authorizationService;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;

    private final CustomPasswordEncoder passwordEncoder;


    @Override
    public Map<String, String> login(UserAuth userAuth) throws Exception {
        Assert.notNull(userAuth.grandType(), BundleUtils.message("grand_type_required"));
        if (userAuth.grandType().equals("password")) {
            Assert.notNull(userAuth.username(), BundleUtils.message("username_required"));
            Assert.notNull(userAuth.password(), BundleUtils.message("password_required"));
            Map<String, String> token = new HashMap<>();
            token.putAll(generateAccessToken(userAuth.username(), userAuth.password(), userAuth.clientId()));
            token.putAll(generateRefreshToken(userAuth.username(), userAuth.clientId()));
            return token;
        } else if (userAuth.grandType().equals("refresh_token")) {
            return generateAccessToken(userAuth.clientId(), userAuth.refreshToken());
        } else throw new Exception(BundleUtils.message("invalid_grand_type"));
    }

    @Override
    public Map<String, String> generateAccessToken(String username, String password, String clientId) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String subject = authentication.getName();
        String scope = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        return buildAccessToken(clientId, subject, scope);
    }

    @Override
    public Map<String, String> generateAccessToken(String clientId, String refreshToken) {
        Jwt decodeJWT = jwtDecoder.decode(refreshToken);
        String subject = decodeJWT.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();
        String scope = grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        return buildAccessToken(clientId, subject, scope);
    }

    private Map<String, String> buildAccessToken(String clientId, String subject, String scope) {
        Instant instant = Instant.now();
        Client client = authorizationService.getClientById(clientId);
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(client.accessTokenTimeout(), ChronoUnit.MINUTES))
                .issuer(client.clientName())
                .claim("scope", scope)
                .claim("client", client.clientName())
                .build();
        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        Map<String, String> token = new HashMap<>();
        token.put("access_token", jwtAccessToken);
        token.put("access_generate_at", String.valueOf(instant));
        token.put("access_expires_at", String.valueOf(client.accessTokenTimeout()));
        return token;
    }

    @Override
    public Map<String, String> generateRefreshToken(String username, String clientId) {
        Instant instant = Instant.now();
        Client client = authorizationService.getClientById(clientId);
        JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                .subject(username)
                .issuedAt(instant)
                .expiresAt(instant.plus(client.refreshTokenTimeout(), ChronoUnit.MINUTES))
                .issuer(client.clientName())
                .claim("client", client.clientName())
                .build();
        String jwtAccessRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
        Map<String, String> token = new HashMap<>();
        token.put("refresh_token", jwtAccessRefreshToken);
        token.put("generateAt", String.valueOf(instant));
        token.put("refresh_expires_at", String.valueOf(client.refreshTokenTimeout()));
        return token;
    }


    @Override
    public String createUser(UserModel user) {
        String id = StringUtils.uuid();
        UserPassword u = (UserPassword) user;
        System.out.println("==> " + passwordEncoder.encode(u.password()));
        UserEntity userEntity = UserEntity.builder()
                .userId(id)
                .firstName(u.firstName())
                .lastName(u.lastName())
                .username(u.username())
                .password(passwordEncoder.encode(u.password()))
                .email(u.email())
                .enabled(true)
                .build();
        userRepository.save(userEntity);
        return id;
    }

    @Override
    public void updateProfile(UserModel user) {
        UserEntity userEntity = userRepository.findByUsernameOrEmail(user.email(), user.email()).orElseThrow(()
                -> new UsernameNotFoundException("Unable to find user " + user.email()));
        userEntity.setFirstName(user.firstName());
        userEntity.setLastName(user.lastName());
        userRepository.save(userEntity);
    }

    @Override
    public void resetUserPassword(ResetPassword resetPassword) throws Exception {
        UserModel user = getUser(resetPassword.userEmail());
    }

    @Override
    public void addUserMoresInfos(UserMoresInfos moresInfos) {

    }

    @Override
    public void updateUserMoresInfos(UserMoresInfos moresInfos) {

    }

    @Override
    public UserModel getUser(String username) {
        UserEntity u = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("Unable to find user " + username));
        return userMapper.mapFromUserEntity(u);
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
    public Page<UserModel> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CompletableFuture<Page<UserEntity>> userEntities = userRepository.findAllBy(pageable);
        return null;
    }

    @Override
    public void createUserSetting(UserSetting userSetting) {

    }

    @Override
    public void editUserSetting(UserSetting userSetting) {

    }

    @Override
    public Page<List<UserModel>> findByGroupId(String groupId) {
        return null;
    }


}
