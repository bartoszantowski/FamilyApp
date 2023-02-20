package com.iitrab.mappers;

import com.iitrab.domain.FamilyEntity;
import com.iitrab.types.FamilyMemberTO;
import com.iitrab.types.FamilyTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FamilyMapper {

    public FamilyTO toFamilyTO(FamilyEntity familyEntity, List<FamilyMemberTO>familyMemberTOs) {
        return new FamilyTO(
                familyEntity.getId(),
                familyEntity.getFamilyName(),
                familyEntity.getNrOfAdults(),
                familyEntity.getNrOfChildren(),
                familyEntity.getNrOfInfants(),
                familyMemberTOs);
    }
}
