package dev.reso.workshop.contract.repository;

import dev.reso.workshop.contract.entities.Contract;
import dev.reso.workshop.contract.enums.ContractType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends MongoRepository<Contract, String> {

    @Query("{ 'manager.name' : { $regex :  ?0, $options: 'i'} }")
    List<Contract>  findContractByManager(String manager);

    List<Contract> findContractByType(ContractType type);
}
