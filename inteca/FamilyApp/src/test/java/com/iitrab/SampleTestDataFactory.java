package com.iitrab;

import com.iitrab.domain.FamilyEntity;
import com.iitrab.types.FamilyMemberTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.UUID.randomUUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SampleTestDataFactory {

    public static FamilyMemberTO AdultFamilyMemberTO() {
        return new FamilyMemberTO(randomUUID().toString(), 20);
    }

    public static FamilyMemberTO ChildFamilyMemberTO() {
        return new FamilyMemberTO(randomUUID().toString(), 10);
    }

    public static FamilyMemberTO InfantFamilyMemberTO() {
        return new FamilyMemberTO(randomUUID().toString(), 2);
    }
}
