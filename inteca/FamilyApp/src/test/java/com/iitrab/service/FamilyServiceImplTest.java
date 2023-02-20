package com.iitrab.service;

import com.iitrab.SampleTestDataFactory;
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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FamilyServiceImplTest {

    @InjectMocks
    private FamilyServiceImpl familyService;
    @Mock
    private FamilyRepo familyRepo;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private FamilyMapper familyMapper;
    @Mock
    private FamilyValidator familyValidator;
    @Mock
    private FamilyMemberMapper familyMemberMapper;

    @Test
    void shouldReturnMatchingFamily_whenFindingFamilyById() {
        Long familyId = 1L;
        String familyName = "TestName";
        FamilyMemberTO child1 = SampleTestDataFactory.InfantFamilyMemberTO();
        FamilyMemberTO adult1 = SampleTestDataFactory.AdultFamilyMemberTO();

        FamilyTO familyTO = new FamilyTO(familyId, familyName, 1, 0, 1, List.of(child1, adult1));

        FamilyEntity tempFamilyEntity = new FamilyEntity(familyId, familyName, 1, 0, 1);

        FamilyMemberTO[] familyMemberTOS = {child1, adult1};

        when(familyRepo.existsById(familyId)).thenReturn(true);
        when(familyRepo.getReferenceById(familyId)).thenReturn(tempFamilyEntity);
        when(restTemplate.getForEntity("http://localhost:8081/v1/familyMembers/{familyId}", FamilyMemberTO[].class, familyId)).thenReturn(new ResponseEntity<>(familyMemberTOS, HttpStatus.OK));
        when(familyMapper.toFamilyTO(tempFamilyEntity, Arrays.stream(familyMemberTOS).toList())).thenReturn(familyTO);

        FamilyTO foundFamily = familyService.getFamily(familyId);

        assertThat(foundFamily).isEqualTo(familyTO);
    }

    @Test
    void shouldReturnFamilyNotFoundException_whenFindingFamilyById() {
        Long familyId = 1L;

        when(familyRepo.existsById(familyId)).thenReturn(false);

        assertThatThrownBy(() -> familyService.getFamily(familyId)).isInstanceOf(
                FamilyNotFoundException.class);
    }

    @Test
    void shouldCreateFamilyAndReturnFamilyId_whenCreatingFamily() {
        Long familyId = 1L;
        String familyName = "TestName";
        FamilyMemberTO adult1 = SampleTestDataFactory.AdultFamilyMemberTO();
        List<FamilyMemberTO> familyMemberTOS = List.of(adult1);

        CreateFamilyRequest createFamilyRequest = new CreateFamilyRequest(familyName, 1, 0, 0, familyMemberTOS);
        CreateFamilyMemberRequest createFamilyMemberRequest = new CreateFamilyMemberRequest(familyId, familyName, adult1.name(), adult1.age());

        doNothing().when(familyValidator).validate(createFamilyRequest);
        when(familyRepo.generateFamilyId(any())).thenReturn(familyId);
        when(familyMemberMapper.toCreateFamilyMemberRequest(adult1, familyId, familyName)).thenReturn(createFamilyMemberRequest);
        when(restTemplate.postForEntity("http://localhost:8081/v1/familyMembers", createFamilyMemberRequest, CreateFamilyMemberRequest.class)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        when(familyRepo.save(any())).thenReturn(any());

        Long expectedFamilyId = familyService.createFamily(createFamilyRequest);

        assertThat(expectedFamilyId).isEqualTo(familyId);
    }
}