package com.product.phoneshop.mapper;

import com.product.phoneshop.config.security.AuthUser;
import com.product.phoneshop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    AuthUser toAuthUser(User user);
}
