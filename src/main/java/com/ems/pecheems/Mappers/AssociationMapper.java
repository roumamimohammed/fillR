package com.ems.pecheems.Mappers;

import com.ems.pecheems.DTO.AssociationDTO;
import com.ems.pecheems.Entities.AssociationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AssociationMapper {

    AssociationMapper INSTANCE = Mappers.getMapper(AssociationMapper.class);

    AssociationDTO toDto(AssociationEntity entity);

    AssociationDTO toDtoPage(AssociationEntity competitionEntity);

    AssociationEntity toEntity( AssociationDTO dto);
}
