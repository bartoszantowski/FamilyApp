package com.iitrab.controller;

import com.iitrab.SampleTestDataFactory;
import com.iitrab.exception.FamilyNotFoundException;
import com.iitrab.service.FamilyServiceImpl;
import com.iitrab.types.CreateFamilyRequest;
import com.iitrab.types.FamilyMemberTO;
import com.iitrab.types.FamilyTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class FamilyControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FamilyServiceImpl familyServiceMock;

    @BeforeEach
    void setUp() {
        FamilyController familyController = new FamilyController(familyServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(familyController)
                .build();
    }

    @Test
    void shouldReturnFamily_whenGettingFamilyById() throws Exception {
        FamilyMemberTO child1 = SampleTestDataFactory.InfantFamilyMemberTO();
        FamilyMemberTO child2 = SampleTestDataFactory.InfantFamilyMemberTO();

        Long familyId = 33L;
        FamilyTO familyTO = new FamilyTO(familyId, "TestName", 0, 0, 2, List.of(child1,child2));

        when(familyServiceMock.getFamily(familyId)).thenReturn(familyTO);

        mockMvc.perform(get("/v1/family/{familyId}", familyId))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(familyTO.name()))
                .andExpect(jsonPath("$.nrOfInfants").value(familyTO.familyMembers().size()))
                .andExpect(jsonPath("$.familyMembers").isNotEmpty())
                .andExpect(jsonPath("$.familyMembers[0].name").value(child1.name()))
                .andExpect(jsonPath("$.familyMembers[1].name").value(child2.name()))
                .andExpect(jsonPath("$.familyMembers[2]").doesNotExist());
    }

    @Test
    void shouldReturnNotFound_whenGettingClientById_thatDoesNotExist() throws Exception {
        long familyId = 1L;
        when(familyServiceMock.getFamily(familyId)).thenThrow(new FamilyNotFoundException("ex" + familyId));

        mockMvc.perform(get("/v1/family/{familyId}", familyId))
                .andDo(log())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnFamilyId_whenCreatingFamily() throws Exception {
        String familyName = "NameTest";
        int nrOfAdults = 0;
        int nrOfChildren = 1;
        int nrOfInfants = 0;
        FamilyMemberTO child1 = SampleTestDataFactory.InfantFamilyMemberTO();
        Long familyId = 12L;

        CreateFamilyRequest createFamilyRequest = new CreateFamilyRequest(familyName, nrOfAdults, nrOfChildren, nrOfInfants, List.of(child1));

        when(familyServiceMock.createFamily(createFamilyRequest)).thenReturn(familyId);

        String requestBody = """
                                {
                                    "familyName": "%s",
                                    "nrOfAdults": %s,
                                    "nrOfChildren": %s,
                                    "nrOfInfants": %s,
                                    "familyMembers": [
                                        {
                                            "name": "%s",
                                            "age": %s
                                        }
                                    ]
                                }
                             """.formatted(familyName, nrOfAdults, nrOfChildren, nrOfInfants, child1.name(), child1.age());

        mockMvc.perform(post("/v1/family")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(log())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(familyId));
    }

    @Test
    void shouldReturnBadRequest_whenCreatingFamily_withEmptyRequestBody() throws Exception {
        String requestBody = "{}";

        mockMvc.perform(post("/v1/family")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(log())
                .andExpect(status().isBadRequest());
    }
}