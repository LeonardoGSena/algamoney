package com.example.algamoney.api.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Address {

    private String streetAddress;
    private String numberAddress;
    private String complement;
    private String province;
    private String zipCode;
    private String city;
    private String state;

}
