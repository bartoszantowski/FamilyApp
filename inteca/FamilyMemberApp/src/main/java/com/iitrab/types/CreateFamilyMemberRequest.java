package com.iitrab.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateFamilyMemberRequest(@NotNull
                                        @JsonProperty("familyId")
                                        Long familyId,
                                        @NotNull
                                        @NotBlank
                                        @JsonProperty("familyName")
                                        String familyName,
                                        @NotNull
                                        @NotBlank
                                        @JsonProperty("name")
                                        String name,
                                        @NotNull
                                        @Positive
                                        @JsonProperty("age")
                                        int age) {
}
