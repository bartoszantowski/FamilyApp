package com.iitrab.dao;


import com.iitrab.domain.FamilyMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberRepo extends JpaRepository<FamilyMemberEntity, Long>, FamilyMemberRepoCustom{
}
