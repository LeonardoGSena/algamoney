package com.example.algamoney.api.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    private String streetAddress;

    @NotBlank
    private String numberAddress;


    private String complement;

    @NotBlank
    private String province;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

}
