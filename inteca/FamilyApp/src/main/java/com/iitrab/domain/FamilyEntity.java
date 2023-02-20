package com.iitrab.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Family")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String familyName;
    private int nrOfAdults;
    private int nrOfChildren;
    private int nrOfInfants;

    public FamilyEntity(String familyName, int nrOfAdults, int nrOfChildren, int nrOfInfants) {
        this.familyName = familyName;
        this.nrOfAdults = nrOfAdults;
        this.nrOfChildren = nrOfChildren;
        this.nrOfInfants = nrOfInfants;
    }
}
