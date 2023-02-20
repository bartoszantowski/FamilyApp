package com.iitrab.validators;

import com.iitrab.SampleTestDataFactory;
import com.iitrab.exception.BusinessException;
import com.iitrab.types.CreateFamilyRequest;
import com.iitrab.types.FamilyMemberTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class FamilyValidatorTest {

    private String familyName;
    private List<FamilyMemberTO> familyMemberTOS;

    @InjectMocks
    private FamilyValidator familyValidator;

    @BeforeEach
    void loadData() {
        familyName = "TestName";
        FamilyMemberTO adult1 = SampleTestDataFactory.AdultFamilyMemberTO();
        FamilyMemberTO adult2 = SampleTestDataFactory.AdultFamilyMemberTO();
        FamilyMemberTO children1 = SampleTestDataFactory.ChildFamilyMemberTO();
        FamilyMemberTO infant1 = SampleTestDataFactory.InfantFamilyMemberTO();
        familyMemberTOS = List.of(adult1, children1, infant1, adult2);
    }

    @Test
    void shouldReturnBusinessException_whenValidatingCreateFamilyRequest_withWrongNumberOfFamilyMembers() {
        CreateFamilyRequest createFamilyRequest = new CreateFamilyRequest(familyName, 1, 1, 1, familyMemberTOS);

        assertThatThrownBy(() -> familyValidator.validate(createFamilyRequest)).isInstanceOf(
                BusinessException.class);
    }

    @Test
    void shouldReturnBusinessException_whenValidatingCreateFamilyRequest_withWrongNumberOfAdults() {
        CreateFamilyRequest createFamilyRequest = new CreateFamilyRequest(familyName, 1, 2, 1, familyMemberTOS);

        assertThatThrownBy(() -> familyValidator.validate(createFamilyRequest)).isInstanceOf(
                BusinessException.class);
    }

    @Test
    void shouldReturnBusinessException_whenValidatingCreateFamilyRequest_withWrongNumberOfChildren() {
        CreateFamilyRequest createFamilyRequest = new CreateFamilyRequest(familyName, 2, 2, 1, familyMemberTOS);

        assertThatThrownBy(() -> familyValidator.validate(createFamilyRequest)).isInstanceOf(
                BusinessException.class);
    }

    @Test
    void shouldReturnBusinessException_whenValidatingCreateFamilyRequest_withWrongNumberOfInfants() {
        CreateFamilyRequest createFamilyRequest = new CreateFamilyRequest(familyName, 2, 1, 2, familyMemberTOS);

        assertThatThrownBy(() -> familyValidator.validate(createFamilyRequest)).isInstanceOf(
                BusinessException.class);
    }
}