package com.product.phoneshop.config.security;

import lombok.Getter;

@Getter


public enum PermissionEnum {
    BRAND_READ("brand:read"),
    BRAND_WRITE("brand:write");

    private final String description;

    PermissionEnum(String description) {
        this.description = description;
    }

}

