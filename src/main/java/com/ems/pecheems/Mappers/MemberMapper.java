package com.ems.pecheems.Mappers;

import com.ems.pecheems.DTO.MemberDTO;
import com.ems.pecheems.Entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO toDtoint(Integer AppUser);
    MemberDTO toDto(AppUser entity);

    AppUser toEntity( MemberDTO dto);
}
