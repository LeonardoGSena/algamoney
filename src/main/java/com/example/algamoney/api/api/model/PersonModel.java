package com.example.algamoney.api.api.model;

import com.example.algamoney.api.domain.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonModel {

    private Long id;
    private String personName;
    private Address address;
    private Boolean activeStatus;

}
