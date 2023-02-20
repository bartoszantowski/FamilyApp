package com.iitrab.dao;

import com.iitrab.domain.FamilyMemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FamilyMemberRepoCustomImpl implements FamilyMemberRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Query finds all family members belonging to the family with the given id.
     *
     * @param familyId family id
     * @return list of all family members
     */
    @Override
    public List<FamilyMemberEntity> getAllFamilyMembers(Long familyId) {
        TypedQuery<FamilyMemberEntity> query = entityManager.createNamedQuery(FamilyMemberEntity.QUERY_FIND_ALL_FAMILY_MEMBERS, FamilyMemberEntity.class);
        query.setParameter("familyId", familyId);

        return query.getResultList();
    }
}
