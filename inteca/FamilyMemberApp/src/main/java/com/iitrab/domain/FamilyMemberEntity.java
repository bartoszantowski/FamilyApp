package com.iitrab.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FamilyMember")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = FamilyMemberEntity.QUERY_FIND_ALL_FAMILY_MEMBERS,
        query = "SELECT fm FROM FamilyMemberEntity fm WHERE familyId=:familyId")
public class FamilyMemberEntity {

    public static final String QUERY_FIND_ALL_FAMILY_MEMBERS = "FamilyMember.findAllFamilyMembers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long familyId;
    private String familyName;
    private String givenName;
    private int age;

    public FamilyMemberEntity(Long familyId, String familyName, String givenName, int age) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.givenName = givenName;
        this.age = age;
    }
}
