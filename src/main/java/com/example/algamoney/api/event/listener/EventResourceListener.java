package com.example.algamoney.api.event.listener;

import com.example.algamoney.api.event.EventResource;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class EventResourceListener implements ApplicationListener<EventResource> {
    @Override
    public void onApplicationEvent(EventResource eventResource) {
        HttpServletResponse response = eventResource.getResponse();
        Long id = eventResource.getId();

        addingHeaderLocation(response, id);
    }

    private void addingHeaderLocation(HttpServletResponse response, Long id) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
