package com.iitrab.controller;

import com.iitrab.service.FamilyService;
import com.iitrab.types.CreateFamilyRequest;
import com.iitrab.types.FamilyTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/family")
@RequiredArgsConstructor
class FamilyController {

    private final FamilyService familyService;

    @GetMapping("/{familyId}")
    public FamilyTO getFamily(@PathVariable("familyId") Long familyId) {
        return familyService.getFamily(familyId);
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public Long createFamily(@RequestBody  @Validated CreateFamilyRequest createFamilyRequest) {
        return familyService.createFamily(createFamilyRequest);
    }
}
