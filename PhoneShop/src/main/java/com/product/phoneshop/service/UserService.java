package com.product.phoneshop.service;

import com.product.phoneshop.config.security.ApplicationUserService;
import com.product.phoneshop.config.security.AuthUser;
import com.product.phoneshop.exception.ServiceException;
import com.product.phoneshop.mapper.UserMapper;
import com.product.phoneshop.model.Role;
import com.product.phoneshop.model.User;
import com.product.phoneshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Primary
@Service
@RequiredArgsConstructor

public class UserService implements ApplicationUserService {

    private final UserRepository userRepository;

    @Override
    public Optional<AuthUser> loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST, "user not found %s".formatted(username)));
        AuthUser authUser = UserMapper.INSTANCE.toAuthUser(user);

        // Assuming you have a method to convert permissions to granted authorities
        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .flatMap(role -> toStreamOfSimpleGrantedAuthority(role))
                .collect(Collectors.toSet());
        authUser.setGrantedAuthorities(authorities);
        return Optional.of(authUser);
    }

    private Stream<SimpleGrantedAuthority> toStreamOfSimpleGrantedAuthority(Role role) {
        Set<SimpleGrantedAuthority> permissions = role.getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        return permissions.stream();
    }

}