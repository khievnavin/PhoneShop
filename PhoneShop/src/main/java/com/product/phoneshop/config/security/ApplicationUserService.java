package com.product.phoneshop.config.security;

import java.util.Optional;

public interface ApplicationUserService {
    Optional<AuthUser> loadUserByUsername(String username);
}
