package com.example.algamoney.api.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

    private String streetAddress;
    private String numberAddress;
    private String complement;
    private String province;
    private String zipCode;
    private String city;
    private String state;

}
