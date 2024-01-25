package com.example.donationmanagement.forfightingagainstglobalhunger;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ForFAGHMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    public abstract ForFAGH toEntity(ForFAGHDto dto);

    public abstract ForFAGHDto toDto(ForFAGH forFAGH);
}
