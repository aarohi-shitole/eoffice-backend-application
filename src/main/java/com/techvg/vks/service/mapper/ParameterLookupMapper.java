package com.techvg.vks.service.mapper;

import com.techvg.vks.domain.ParameterLookup;
import com.techvg.vks.service.dto.ParameterLookupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ParameterLookup} and its DTO {@link ParameterLookupDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParameterLookupMapper extends EntityMapper<ParameterLookupDTO, ParameterLookup> {}
