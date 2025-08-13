package com.trybank.trybank_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trybank.trybank_backend.domain.dto.person.PersonCreateRequest;
import com.trybank.trybank_backend.domain.dto.person.PersonResponse;
import com.trybank.trybank_backend.domain.dto.person.PersonUpdateRequest;
import com.trybank.trybank_backend.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResponse> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public PersonResponse getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonResponse createPerson(
            @RequestBody PersonCreateRequest request) {
        return personService.createPerson(request);
    }

    @PutMapping("/{id}")
    public PersonResponse updatePerson(
            @PathVariable String id,
            @RequestBody PersonUpdateRequest request) {
        return personService.updatePerson(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable String id) {
        personService.deletePerson(id);
    }
}
