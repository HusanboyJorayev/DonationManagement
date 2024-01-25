package com.example.donationmanagement.forplantingtrees;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ForPlantingTressMapper {

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createdAt")
 /*   @Mapping(ignore = true, target = "allBalance")
    @Mapping(ignore = true, target = "username")*/
    public abstract ForPlantingTrees toEntity(ForPlantingTreesDto dto);

    public abstract ForPlantingTreesDto toDto(ForPlantingTrees forPlantingTrees);
}
