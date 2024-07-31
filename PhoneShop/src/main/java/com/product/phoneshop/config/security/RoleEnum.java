package com.product.phoneshop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static com.product.phoneshop.config.security.PermissionEnum.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public enum RoleEnum{

    ADMIN(Set.of(BRAND_READ, BRAND_WRITE)),
    SALE(Set.of(BRAND_READ));

    private final Set<PermissionEnum> permissions;

//    RoleEnum(Set<PermissionEnum> permissions) {
//        this.permissions = permissions;
//    }

    public Set<SimpleGrantedAuthority> getAuthorities() {

        return this
             .getPermissions()
             .stream()
             .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
             .collect(Collectors.toSet());
    }
}
