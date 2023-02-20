package com.iitrab.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record CreateFamilyMemberRequest(@NotNull
                                        @JsonProperty("familyId")
                                        Long familyId,

                                        @NotNull
                                        @JsonProperty("familyName")
                                        String familyName,

                                        @NotNull
                                        @JsonProperty("name")
                                        String name,

                                        @NotNull
                                        @Positive
                                        @JsonProperty("age")
                                        int age) {
}
