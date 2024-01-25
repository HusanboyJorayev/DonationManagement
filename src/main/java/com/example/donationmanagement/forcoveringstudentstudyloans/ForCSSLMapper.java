package com.example.donationmanagement.forcoveringstudentstudyloans;

import com.example.donationmanagement.forplantingtrees.ForPlantingTrees;
import com.example.donationmanagement.forplantingtrees.ForPlantingTreesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ForCSSLMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
    public abstract ForCSSL toEntity(ForCSSLDto dto);

    public abstract ForCSSLDto toDto(ForCSSL forCSSL);
}
