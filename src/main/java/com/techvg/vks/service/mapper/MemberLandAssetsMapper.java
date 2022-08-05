package com.techvg.vks.service.mapper;

import com.techvg.vks.domain.MemberLandAssets;
import com.techvg.vks.service.dto.MemberLandAssetsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MemberLandAssets} and its DTO {@link MemberLandAssetsDTO}.
 */
@Mapper(componentModel = "spring")
public interface MemberLandAssetsMapper extends EntityMapper<MemberLandAssetsDTO, MemberLandAssets> {}
