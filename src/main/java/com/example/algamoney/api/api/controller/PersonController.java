package com.example.algamoney.api.api.controller;

import com.example.algamoney.api.api.assembler.PersonMapper;
import com.example.algamoney.api.api.model.PersonModel;
import com.example.algamoney.api.api.model.input.PersonInput;
import com.example.algamoney.api.domain.model.Person;
import com.example.algamoney.api.domain.repository.PersonRepository;
import com.example.algamoney.api.domain.service.PersonService;
import com.example.algamoney.api.event.EventResource;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {

    private PersonService personService;
    private PersonMapper personMapper;
    private PersonRepository personRepository;
    private ApplicationEventPublisher publisher;


    @GetMapping
    private List<PersonModel> getPersons() {
        List<Person> personList = personService.getAllPersons();
        return personMapper.toCollectionModel(personList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonModel> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id)
                .map(person -> ResponseEntity.ok(personMapper.toModel(person)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PersonModel addNewPerson(@Valid @RequestBody PersonInput personInput, HttpServletResponse response) {
        Person person = personMapper.toEntity(personInput);
        Person newPerson = personService.addNewPerson(person);

        publisher.publishEvent(new EventResource(this, response, person.getId()));

        return personMapper.toModel(newPerson);
    }

}
