package com.example.algamoney.api.domain.service;

import com.example.algamoney.api.domain.exception.BusinessException;
import com.example.algamoney.api.domain.model.Person;
import com.example.algamoney.api.domain.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {

    private PersonRepository personRepository;


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Transactional
    public Person addNewPerson(Person person) {
        return personRepository.save(person);
    }
}
