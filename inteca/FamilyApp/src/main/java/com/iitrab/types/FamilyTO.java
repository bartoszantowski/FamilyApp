package com.iitrab.types;

import java.util.List;

public record FamilyTO(Long id,
                       String name,
                       int nrOfAdults,
                       int nrOfChildren,
                       int nrOfInfants,
                       List<FamilyMemberTO> familyMembers) {
}
