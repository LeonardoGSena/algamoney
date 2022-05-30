package com.example.algamoney.api.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PersonInput {

    @Valid
    @NotBlank
    private String personName;

    @Valid
    @NotNull
    private AddressInput address;

    @Valid
    @NotNull
    private Boolean activeStatus;

}
