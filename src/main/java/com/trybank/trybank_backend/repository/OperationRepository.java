package com.trybank.trybank_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trybank.trybank_backend.domain.model.Operation;

public interface OperationRepository extends MongoRepository<Operation, String>{
    List<Operation> findByAccountSourceId(String accountSourceId);
    List<Operation> findByAccountDestinationId(String accountDestinationId);
}
