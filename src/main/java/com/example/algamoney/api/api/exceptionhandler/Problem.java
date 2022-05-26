package com.example.algamoney.api.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problem {

    private Integer status;
    private OffsetDateTime dateHour;
    private String title;
    private List<Body> body;

    @AllArgsConstructor
    @Getter
    public static class Body {

        private String name;
        private String message;

    }

}
