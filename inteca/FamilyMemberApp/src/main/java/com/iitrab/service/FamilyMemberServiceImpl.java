package com.iitrab.service;

import com.iitrab.dao.FamilyMemberRepo;
import com.iitrab.domain.FamilyMemberEntity;
import com.iitrab.types.CreateFamilyMemberRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberRepo familyMemberRepo;

    @Override
    public List<FamilyMemberEntity> getAllFamilyMembers(Long familyId) {
        return familyMemberRepo.getAllFamilyMembers(familyId);
    }

    @Override
    public void createFamilyMember(CreateFamilyMemberRequest createFamilyMemberRequest) {
        FamilyMemberEntity familyMemberEntity = new FamilyMemberEntity(
                createFamilyMemberRequest.familyId(),
                createFamilyMemberRequest.familyName(),
                createFamilyMemberRequest.name(),
                createFamilyMemberRequest.age());

        log.info("Creating the family member...");
        familyMemberRepo.save(familyMemberEntity);
        log.info("Save the family member {}", familyMemberEntity);
    }
}
