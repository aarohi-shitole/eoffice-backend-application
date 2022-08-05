package com.techvg.vks.service.mapper;

import com.techvg.vks.domain.Member;
import com.techvg.vks.domain.MemberBank;
import com.techvg.vks.domain.Society;
import com.techvg.vks.service.dto.MemberBankDTO;
import com.techvg.vks.service.dto.MemberDTO;
import com.techvg.vks.service.dto.SocietyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Member} and its DTO {@link MemberDTO}.
 */
@Mapper(componentModel = "spring")
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {
    @Mapping(target = "memberBank", source = "memberBank", qualifiedByName = "memberBankId")
    @Mapping(target = "society", source = "society", qualifiedByName = "societyId")
    MemberDTO toDto(Member s);

    @Named("memberBankId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MemberBankDTO toDtoMemberBankId(MemberBank memberBank);

    @Named("societyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SocietyDTO toDtoSocietyId(Society society);
}
