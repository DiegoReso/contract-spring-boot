package dev.reso.workshop.contract.service;

import dev.reso.workshop.contract.entities.Contract;
import dev.reso.workshop.contract.enums.ContractType;
import dev.reso.workshop.contract.exceptions.EntityNotFound;
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

    public Contract findContractById(String id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Contract with ID " + id + " not found"));
    }

    public Contract updateContract(String id, Contract contractRequest){
        Contract contract = updateContractHelper(id, contractRequest);
        return repository.save(contract);
    }

    public void deleteContract(String id){
        Contract contract = findContractById(id);
        repository.delete(contract);
    }

    public List<Contract>  findContractByManager(String manager){
        return repository.findContractByManager(manager);
    }

    public List<Contract> findContractByType(ContractType type){
            return repository.findContractByType(type);
    }

    public Contract updateContractHelper(String id, Contract contractRequest){
        Contract contract = findContractById(id);
        contract.setName(contractRequest.getName());
        contract.setNumber(contractRequest.getNumber());
        contract.setBalance(contractRequest.getBalance());
        contract.setTotal(contractRequest.getTotal());
        contract.setType(contractRequest.getType());
        contract.setRating(contractRequest.getRating());
        contract.setManager(contractRequest.getManager());
        contract.setPdfPathFile(contractRequest.getPdfPathFile());
        contract.setInitiationContract(contractRequest.getInitiationContract());
        contract.setEndContract(contractRequest.getEndContract());
        contract.setModality(contractRequest.getModality());

        return contract;
    }
}
