package com.iitrab.mappers;

import com.iitrab.domain.FamilyMemberEntity;
import com.iitrab.types.FamilyMemberTO;
import org.springframework.stereotype.Component;

@Component
public class FamilyMemberMapper {

    public FamilyMemberTO toFamilyMemberTO(FamilyMemberEntity familyMemberEntity) {
        return new FamilyMemberTO(familyMemberEntity.getGivenName(),
                                  familyMemberEntity.getAge());
    }
}
