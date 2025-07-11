package dev.reso.workshop.contract.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.reso.workshop.contract.controller.request.ContractRequest;
import dev.reso.workshop.contract.controller.response.ContractResponse;
import dev.reso.workshop.contract.entities.Contract;
import dev.reso.workshop.contract.exceptions.FileEmptyException;

import dev.reso.workshop.contract.mapper.ContractMapper;
import dev.reso.workshop.contract.service.ContractService;
import dev.reso.workshop.contract.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
@CrossOrigin(origins = "http://localhost:3000")
public class ContractController {

    @Autowired
    private ContractService service;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private FileStorageService fileStorageService;


    @GetMapping
    public List<ContractResponse> findAllContracts(){
        List<Contract> contracts = service.findAllContracts();
        return contracts.stream().map(ContractMapper::toContractResponse).toList();
    }

    @PostMapping
    public ResponseEntity<ContractResponse>  insertContract(
            @RequestParam("file") MultipartFile file,
            @RequestParam("contractData") String contractJson) throws JsonProcessingException {

        if (file.isEmpty()) {
            throw new FileEmptyException("The file is empty, choose a pdf file");
        }

        String pdfFilePath = fileStorageService.saveFile(file);
        ContractRequest contractRequest = objectMapper.readValue(contractJson, ContractRequest.class);
        Contract contract = ContractMapper.toContract(contractRequest);
        contract.setPdfPathFile(pdfFilePath);
        Contract newContract = service.insertContract(contract);
        ContractResponse contractResponse = ContractMapper.toContractResponse(newContract);
        return new ResponseEntity<>(contractResponse, HttpStatus.CREATED);
    }
}
