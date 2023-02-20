package com.iitrab.dao;

import com.iitrab.domain.FamilyMemberEntity;

import java.util.List;

public interface FamilyMemberRepoCustom {
    List<FamilyMemberEntity> getAllFamilyMembers(Long familyId);
}
