package com.iitrab.dao;

import com.iitrab.domain.FamilyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepo extends JpaRepository<FamilyEntity, Long>, FamilyRepoCustom{
}
