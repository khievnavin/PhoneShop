package com.product.phoneshop.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public enum RoleEnum {
    ADMIN(Set.of(PermissionEnum.BRAND_READ, PermissionEnum.BRAND_WRITE)),
    SALE(Set.of(PermissionEnum.BRAND_READ));

    private final Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> permissions = this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
