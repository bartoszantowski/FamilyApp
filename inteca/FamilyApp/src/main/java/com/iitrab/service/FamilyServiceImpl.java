package com.iitrab.service;

import com.iitrab.dao.FamilyRepo;
import com.iitrab.domain.FamilyEntity;
import com.iitrab.exception.FamilyNotFoundException;
import com.iitrab.mappers.FamilyMapper;
import com.iitrab.mappers.FamilyMemberMapper;
import com.iitrab.types.CreateFamilyMemberRequest;
import com.iitrab.types.CreateFamilyRequest;
import com.iitrab.types.FamilyMemberTO;
import com.iitrab.types.FamilyTO;
import com.iitrab.validators.FamilyValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FamilyServiceImpl implements FamilyService {

    private static final String FAMILY_WITH_ID = "Family with id:";
    private static final String DOESN_T_EXIST = " doesn't exist!!!";
    public static final String LIST_OF_FAMILY_MEMBERS_IS_EMPTY = "List of family members is empty!";
    public static final String FAMILY_MEMBERS_URL = "http://family-member-app:8081/v1/familyMembers";
    public static final String FAMILY_ID = "/{familyId}";


    private final FamilyRepo familyRepo;
    private final RestTemplate restTemplate;
    private final FamilyMapper familyMapper;
    private final FamilyValidator familyValidator;
    private final FamilyMemberMapper familyMemberMapper;


    @Override
    public FamilyTO getFamily(Long familyId) {
        ifFamilyExist(familyId);

        FamilyEntity tempFamilyEntity = familyRepo.getReferenceById(familyId);

        ResponseEntity<FamilyMemberTO[]> response = getResponseEntity(familyId);

        List<FamilyMemberTO> familyMemberTOs = Arrays.asList(response.getBody());

        checkFamilyMemberTOs(familyMemberTOs);

        return mapToFamilyTO(tempFamilyEntity, familyMemberTOs);
    }

    @Override
    public Long createFamily(CreateFamilyRequest createFamilyRequest) {
        familyValidator.validate(createFamilyRequest);

        FamilyEntity family = new FamilyEntity(createFamilyRequest.familyName(),
                                               createFamilyRequest.nrOfAdults(),
                                               createFamilyRequest.nrOfChildren(),
                                               createFamilyRequest.nrOfInfants());

        Long familyId = familyRepo.generateFamilyId(family);

        List<FamilyMemberTO> familyMemberTOs = createFamilyRequest.familyMembers();

        createFamilyMembers(family, familyId, familyMemberTOs);

        familyRepo.save(family);

        return familyId;
    }


    private FamilyTO mapToFamilyTO(FamilyEntity tempFamilyEntity, List<FamilyMemberTO> familyMemberTOs) {
        return familyMapper.toFamilyTO(tempFamilyEntity, familyMemberTOs);
    }

    private void checkFamilyMemberTOs(List<FamilyMemberTO> familyMemberTOs) {
        if (familyMemberTOs.isEmpty()) {
            throw new FamilyNotFoundException(LIST_OF_FAMILY_MEMBERS_IS_EMPTY);
        }
    }

    private ResponseEntity<FamilyMemberTO[]> getResponseEntity(Long familyId) {
        return restTemplate.getForEntity(
                FAMILY_MEMBERS_URL + FAMILY_ID,
                FamilyMemberTO[].class,
                familyId
        );
    }

    private void createFamilyMembers(FamilyEntity family, Long familyId, List<FamilyMemberTO> familyMemberTOs) {
        familyMemberTOs.forEach(familyMemberTO -> restTemplate.postForEntity(
                FAMILY_MEMBERS_URL,
                familyMemberMapper.toCreateFamilyMemberRequest(familyMemberTO, familyId, family.getFamilyName()),
                CreateFamilyMemberRequest.class
        ));
    }

    private void ifFamilyExist(Long familyId) {
        if (!familyRepo.existsById(familyId)) {
            throw new FamilyNotFoundException(FAMILY_WITH_ID + familyId + DOESN_T_EXIST);
        }
    }
}
