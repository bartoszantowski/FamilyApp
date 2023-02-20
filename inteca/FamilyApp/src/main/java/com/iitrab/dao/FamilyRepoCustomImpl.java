package com.iitrab.dao;

import com.iitrab.domain.FamilyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class FamilyRepoCustomImpl implements FamilyRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Make an instance entity managed and persistent
     * and generate id for this entity.
     *
     * @param family FamilyEntity
     * @return created familyId
     */
    @Override
    public Long generateFamilyId(FamilyEntity family) {
        entityManager.persist(family);
        return family.getId();
    }
}
