package com.iitrab.controller;

import com.iitrab.mappers.FamilyMemberMapper;
import com.iitrab.service.FamilyMemberService;
import com.iitrab.types.CreateFamilyMemberRequest;
import com.iitrab.types.FamilyMemberTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/familyMembers")
@RequiredArgsConstructor
class FamilyMemberController {

    private final FamilyMemberService familyMemberService;
    private final FamilyMemberMapper familyMemberMapper;

    @GetMapping("/{familyId}")
    public List<FamilyMemberTO> searchFamilyMembers(@PathVariable("familyId") Long familyId) {
        return familyMemberService.getAllFamilyMembers(familyId).stream()
                .map(familyMemberMapper::toFamilyMemberTO)
                .toList();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createFamilyMember(@RequestBody @Validated CreateFamilyMemberRequest createFamilyMemberRequest) {
        familyMemberService.createFamilyMember(createFamilyMemberRequest);
    }
}
