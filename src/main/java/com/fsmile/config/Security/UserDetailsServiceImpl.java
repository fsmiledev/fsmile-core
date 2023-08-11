package com.fsmile.config.Security;

import com.fsmile.domains.authorization.entities.GroupEntity;
import com.fsmile.domains.authorization.repositories.GroupRepository;
import com.fsmile.domains.user.entities.UserEntity;
import com.fsmile.domains.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project fsmile-core
 * Package com.fsmile.config.Security
 * Author revouna
 * Date 08/08/2023
 */

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("Unable to find user " + username));
        System.out.println(user.getUsername());
        System.out.println("password " + user.getPassword());
        List<GroupEntity> groups = groupRepository.findByUserId(user.getUserId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        groups.forEach(group -> authorities.add( new SimpleGrantedAuthority(group.getName())));
        return new User(username, user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }
}
