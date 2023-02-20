package com.iitrab.mappers;

import com.iitrab.types.CreateFamilyMemberRequest;
import com.iitrab.types.FamilyMemberTO;
import org.springframework.stereotype.Component;

@Component
public class FamilyMemberMapper {

    public CreateFamilyMemberRequest toCreateFamilyMemberRequest(FamilyMemberTO familyMemberTO, Long familyId, String familyName) {
        return CreateFamilyMemberRequest.builder()
                .familyId(familyId)
                .familyName(familyName)
                .name(familyMemberTO.name())
                .age(familyMemberTO.age())
                .build();
    }
}
