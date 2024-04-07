package com.dpd.demo.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "addresses")
public class AddressEntity extends EntityBase {

    private int postCode;
    private String city;
    private String street;
    private String houseNumber;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;

}
