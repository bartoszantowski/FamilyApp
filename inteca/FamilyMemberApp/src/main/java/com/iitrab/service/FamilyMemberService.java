package com.iitrab.service;

import com.iitrab.domain.FamilyMemberEntity;
import com.iitrab.types.CreateFamilyMemberRequest;

import java.util.List;

public interface FamilyMemberService {

    /**
     * Returns all family members by given family id.
     *
     * @param familyId family id
     * @return list of family members
     */
    List<FamilyMemberEntity> getAllFamilyMembers(Long familyId);

    void createFamilyMember(CreateFamilyMemberRequest createFamilyMemberRequest);
}
