package com.iitrab.validators;

import com.iitrab.exception.BusinessException;
import com.iitrab.types.CreateFamilyRequest;
import com.iitrab.types.FamilyMemberTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class for family validation.
 * Checks the elements of the compliance of the given family with applicable regulations.
 * Checks whether the family data in the form corresponds to the data of family members.
 */
@Component
@Slf4j
public class FamilyValidator {

    private static final int  START_CHILDREN_AGE = 4;
    private static final int  START_ADULT_AGE = 16;

    private int tempNrOfAdults;
    private int tempNrOfChildren;
    private int tempNrOfInfants;
    
    public void validate(CreateFamilyRequest createFamilyRequest) {
        assignFamilyMembersToAppropriateAgeCategories(createFamilyRequest);

        checkIfTheNumberOfFamilyMembersInAgeCategoriesIsConsistentWithTheForm(createFamilyRequest);
    }

    private void checkIfTheNumberOfFamilyMembersInAgeCategoriesIsConsistentWithTheForm(CreateFamilyRequest createFamilyRequest) {
        if (tempNrOfAdults != createFamilyRequest.nrOfAdults())
            throw new BusinessException("Number of adults is incorrect!");
        if (tempNrOfChildren != createFamilyRequest.nrOfChildren())
            throw new BusinessException("Number of children is incorrect!");
        if (tempNrOfInfants != createFamilyRequest.nrOfInfants())
            throw new BusinessException("Number of infants is incorrect!");
    }

    private void assignFamilyMembersToAppropriateAgeCategories(CreateFamilyRequest createFamilyRequest) {
        for (FamilyMemberTO familyMember : createFamilyRequest.familyMembers()) {
            if (familyMember.age() < START_CHILDREN_AGE)
                tempNrOfInfants++;
            else if (familyMember.age() >= START_ADULT_AGE)
                tempNrOfAdults++;
            else if (familyMember.age() < START_ADULT_AGE && familyMember.age() >= START_CHILDREN_AGE)
                tempNrOfChildren++;
        }
    }
}
