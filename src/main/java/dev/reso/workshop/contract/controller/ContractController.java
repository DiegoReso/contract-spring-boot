package dev.reso.workshop.contract.controller;

import dev.reso.workshop.contract.controller.request.ContractRequest;
import dev.reso.workshop.contract.controller.response.ContractResponse;
import dev.reso.workshop.contract.entities.Contract;
import dev.reso.workshop.contract.mapper.ContractMapper;
import dev.reso.workshop.contract.service.ContractService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService service;

    @GetMapping
    public List<ContractResponse> findAllContracts(){
        List<Contract> contracts = service.findAllContracts();
        return contracts.stream().map(ContractMapper::toContractResponse).toList();
    }

    @PostMapping
    public ContractResponse insertConctract(@RequestBody ContractRequest contractRequest){
        Contract contract = ContractMapper.toContract(contractRequest);
        Contract newContract = service.insertContract(contract);
        return ContractMapper.toContractResponse(newContract);
    }
}
