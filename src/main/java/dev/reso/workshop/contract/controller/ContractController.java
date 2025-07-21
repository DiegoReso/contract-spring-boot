package dev.reso.workshop.contract.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.reso.workshop.contract.controller.request.ContractRequest;
import dev.reso.workshop.contract.controller.response.ContractResponse;
import dev.reso.workshop.contract.entities.Contract;
import dev.reso.workshop.contract.enums.ContractType;
import dev.reso.workshop.contract.exceptions.ContractValidationException;
import dev.reso.workshop.contract.exceptions.EntityNotFound;
import dev.reso.workshop.contract.exceptions.FileEmptyException;
import dev.reso.workshop.contract.mapper.ContractMapper;
import dev.reso.workshop.contract.service.ContractService;
import dev.reso.workshop.contract.util.FileStorageService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contracts")
@CrossOrigin(origins = "http://localhost:3000")
public class ContractController {

    private final ContractService service;
    private final ObjectMapper objectMapper;
    private final FileStorageService fileStorageService;
    private final Validator validator;


    @GetMapping
    public ResponseEntity<List<ContractResponse>>  findAllContracts(){
        List<Contract> contracts = service.findAllContracts();
        List<ContractResponse> list = contracts.stream().map(ContractMapper::toContractResponse).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse>  findById(@PathVariable String id){
        Contract contract = service.findContractById(id);
        ContractResponse contractResponse = ContractMapper.toContractResponse(contract);
        return ResponseEntity.ok(contractResponse);
    }

    @GetMapping("/manager-search")
    public  ResponseEntity<List<ContractResponse>> findContractByManager(@RequestParam(value = "manager", defaultValue = "") String manager){
        List<ContractResponse> contractResponses  = service.findContractByManager(manager).stream().map(ContractMapper::toContractResponse).toList();
        return ResponseEntity.ok(contractResponses);
    }

    @GetMapping("/type-search")
    public ResponseEntity<List<ContractResponse>> findContractByType(@RequestParam(value = "type", defaultValue = "") String type){
        try{
            ContractType contractType = ContractType.valueOf(type.toUpperCase());
            List<Contract> contracts = service.findContractByType(contractType);
            List<ContractResponse> list = contracts.stream().map(ContractMapper::toContractResponse).toList();
            return ResponseEntity.ok(list);
        }catch (IllegalArgumentException e){
            throw new EntityNotFound("Type not found");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractResponse> updateContract(
            @PathVariable String id,
            @RequestParam("file") MultipartFile file,
            @RequestParam("contractData") String contractJson) throws  JsonProcessingException{

        if (file.isEmpty()) {
            throw new FileEmptyException("The file is empty, choose a pdf file");
        }

        String pdfFilePath = fileStorageService.saveFile(file);
        ContractRequest contractRequest = objectMapper.readValue(contractJson, ContractRequest.class);
        Contract contract = ContractMapper.toContract(contractRequest);
        contract.setPdfPathFile(pdfFilePath);
        Contract newContract = service.updateContract(id,contract);
        ContractResponse contractResponse = ContractMapper.toContractResponse(newContract);
        return new ResponseEntity<>(contractResponse, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<ContractResponse>  insertContract(
            @RequestParam("file") MultipartFile file,
            @RequestParam("contractData") String contractJson) throws JsonProcessingException {

        if (file.isEmpty()) {
            throw new FileEmptyException("The file is empty, choose a pdf file");
        }

        ContractRequest contractRequest = objectMapper.readValue(contractJson, ContractRequest.class);

        Set<ConstraintViolation<ContractRequest>> violations = validator.validate(contractRequest);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.joining(", "));
            throw new ContractValidationException("Validation failed for ContractRequest: " + errorMessage);
        }

        String pdfFilePath = fileStorageService.saveFile(file);
        Contract contract = ContractMapper.toContract(contractRequest);
        contract.setPdfPathFile(pdfFilePath);
        Contract newContract = service.insertContract(contract);
        ContractResponse contractResponse = ContractMapper.toContractResponse(newContract);
        return new ResponseEntity<>(contractResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable String id){
        service.deleteContract(id);
        return ResponseEntity.noContent().build();
    }
}
