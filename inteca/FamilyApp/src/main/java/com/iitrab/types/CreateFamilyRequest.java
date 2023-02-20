package com.iitrab.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record CreateFamilyRequest (@NotNull
                                   @NotBlank
                                   @JsonProperty("familyName")
                                   String familyName,

                                   @NotNull
                                   @PositiveOrZero
                                   @JsonProperty("nrOfAdults")
                                   int nrOfAdults,

                                   @NotNull
                                   @PositiveOrZero
                                   @JsonProperty("nrOfChildren")
                                   int nrOfChildren,

                                   @NotNull
                                   @PositiveOrZero
                                   @JsonProperty("nrOfInfants")
                                   int nrOfInfants,

                                   @JsonProperty("familyMembers")
                                   @JsonDeserialize
                                   @NotEmpty
                                   @Valid
                                   List<FamilyMemberTO> familyMembers
) {
}

