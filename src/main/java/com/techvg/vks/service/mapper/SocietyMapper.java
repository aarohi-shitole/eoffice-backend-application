package com.techvg.vks.service.mapper;

import com.techvg.vks.domain.Society;
import com.techvg.vks.service.dto.SocietyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Society} and its DTO {@link SocietyDTO}.
 */
@Mapper(componentModel = "spring")
public interface SocietyMapper extends EntityMapper<SocietyDTO, Society> {
    @Mapping(target = "society", source = "society", qualifiedByName = "societyId")
    SocietyDTO toDto(Society s);

    @Named("societyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SocietyDTO toDtoSocietyId(Society society);
}
