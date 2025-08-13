package com.trybank.trybank_backend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trybank.trybank_backend.domain.model.Person;

public interface PersonRepository extends MongoRepository<Person, String>{
    Optional<Person> findByUsername(String username);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
