package com.example.donationmanagement.cart;


import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    public abstract Cart toEntity(CartDto dto);


    public abstract CartDto toDto(Cart cart);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Cart cart, CartDto dto);
}
