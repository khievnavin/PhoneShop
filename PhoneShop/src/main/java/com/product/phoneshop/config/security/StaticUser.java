package com.product.phoneshop.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaticUser implements ApplicationUserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AuthUser> loadUserByUsername(String username) {
        return getAuthUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    private List<AuthUser> getAuthUsers(){
        AuthUser vin = new AuthUser("vin", passwordEncoder.encode("vin"), RoleEnum.SALE.getAuthorities(),  true, true, true, true);
        AuthUser davin = new AuthUser("davin", passwordEncoder.encode("davin"),RoleEnum.ADMIN.getAuthorities(),  true, true, true, true);
        return List.of(vin,davin);
    }

}
