package dev.reso.workshop.contract.repository;

import dev.reso.workshop.contract.entities.Contract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {
}
