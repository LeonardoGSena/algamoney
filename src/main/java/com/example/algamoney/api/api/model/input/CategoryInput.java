package com.example.algamoney.api.api.model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryInput {

    @NotBlank
    private String name;
}
