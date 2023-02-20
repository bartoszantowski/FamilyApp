package com.iitrab.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FamilyMemberTO(@NotNull
                             @NotBlank
                             @JsonProperty("name")
                             String name,
                             @NotNull
                             @Positive
                             @JsonProperty("age")
                             int age) {
}
