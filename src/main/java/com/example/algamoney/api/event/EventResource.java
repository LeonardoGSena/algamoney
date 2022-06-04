package com.example.algamoney.api.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class EventResource extends ApplicationEvent {

    private HttpServletResponse response;
    private Long id;

    public EventResource(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }
}
