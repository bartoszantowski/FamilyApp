package com.iitrab.service;

import com.iitrab.types.CreateFamilyRequest;
import com.iitrab.types.FamilyTO;

public interface FamilyService {

    /**
     * Returns family by given id.
     *
     * @param familyId id of the family to search
     * @return family
     */
    FamilyTO getFamily(Long familyId);

    /**
     * Creates and persists the family, based on the provided creation data.
     *
     * @param createFamilyRequest data of the created family
     * @return family id
     */
    Long createFamily(CreateFamilyRequest createFamilyRequest);
}
