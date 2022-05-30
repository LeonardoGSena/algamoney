package com.example.algamoney.api.api.assembler;

import com.example.algamoney.api.api.model.PersonModel;
import com.example.algamoney.api.api.model.input.PersonInput;
import com.example.algamoney.api.domain.model.Person;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PersonMapper {

    private ModelMapper modelMapper;


    public PersonModel toModel(Person person) {
        return modelMapper.map(person, PersonModel.class);
    }

    public Person toEntity(PersonInput personInput) {
        return modelMapper.map(personInput, Person.class);
    }

    public List<PersonModel> toCollectionModel(List<Person> allPersons) {
        return allPersons.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
