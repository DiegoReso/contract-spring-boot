package dev.reso.workshop.contract.service;

import dev.reso.workshop.contract.entities.Contract;
import dev.reso.workshop.contract.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository repository;

    public Contract insertContract(Contract contractRequest){
        return repository.insert(contractRequest);
    }

    public List<Contract> findAllContracts() {
        return repository.findAll();
    }
}
