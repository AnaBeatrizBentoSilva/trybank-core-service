package com.trybank.trybank_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trybank.trybank_backend.domain.dto.person.PersonCreateRequest;
import com.trybank.trybank_backend.domain.dto.person.PersonResponse;
import com.trybank.trybank_backend.domain.dto.person.PersonUpdateRequest;
import com.trybank.trybank_backend.domain.model.Person;
import com.trybank.trybank_backend.exception.BusinessException;
import com.trybank.trybank_backend.exception.NotFoundException;
import com.trybank.trybank_backend.repository.PersonRepository;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonResponse> getAllPeople() {
        return personRepository.findAll()
                .stream()
                .map(PersonResponse::fromEntity)
                .toList();
    }

    public PersonResponse getPersonById(String id) {
        return personRepository.findById(id)
                .map(PersonResponse::fromEntity)
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));
    }

    @Transactional
    public PersonResponse createPerson(PersonCreateRequest request) {
        if (personRepository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF already in use");
        }

        if (personRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already in use");
        }
        if (personRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("Username already in use");
        }

        Person person = new Person();
        person.setFullName(request.getFullName());
        person.setDateOfBirth(request.getDateOfBirth());
        person.setCpf(request.getCpf());
        person.setEmail(request.getEmail());
        person.setPhoneNumber(request.getPhoneNumber());
        person.setAddress(request.getAddress());
        person.setUsername(request.getUsername());
        person.setPassword(request.getPassword());

        return PersonResponse.fromEntity(personRepository.save(person));
    }

    @Transactional
    public PersonResponse updatePerson(String id, PersonUpdateRequest request) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person not found with id: " + id));

        if (request.getUsername() != null && !request.getUsername().equalsIgnoreCase(person.getUsername())) {
            if (personRepository.existsByUsername(request.getUsername())) {
                throw new BusinessException("Username already in use");
            }
            person.setUsername(request.getUsername());
        }
        
        if (request.getEmail() != null && !request.getEmail().equalsIgnoreCase(person.getEmail())) {
            if (personRepository.existsByEmail(request.getEmail())) {
                throw new BusinessException("Email already in use");
            }
            person.setEmail(request.getEmail());
        }

        if (request.getPhoneNumber() != null) {
            person.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getAddress() != null) {
            person.setAddress(request.getAddress());
        }

        return PersonResponse.fromEntity(personRepository.save(person));
    }

    public void deletePerson(String id) {
        if (!personRepository.existsById(id)) {
            throw new NotFoundException("Person not found with id: " + id);
        }
        personRepository.deleteById(id);
    }
}
