package com.example.donationmanagement.user;


import com.example.donationmanagement.cart.CartMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = Collectors.class)
public abstract class UserMapper {

    @Autowired
    protected CartMapper cartMapper;


    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(ignore = true, target = "carts")
    public abstract User toEntity(UserDto dto);


    @Mapping(ignore = true, target = "carts")
    public abstract UserDto toDto(User user);

    @Mapping(target = "carts", expression = "java(user.getCarts().stream().map(this.cartMapper::toDto).toList())")
    public abstract UserDto toDtoWithCarts(User user);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);


    public void view(User user, UserDto dto) {
        dto.setCarts(user.getCarts().stream().map(this.cartMapper::toDto).toList());
    }

}


