package dev.reso.workshop.contract.mapper;

import dev.reso.workshop.contract.controller.request.ContractRequest;
import dev.reso.workshop.contract.controller.response.ContractResponse;
import dev.reso.workshop.contract.entities.Contract;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContractMapper {

    public static Contract toContract(ContractRequest contractRequest){
        return Contract.builder()
                .name(contractRequest.name())
                .number(contractRequest.number())
                .balance(contractRequest.balance())
                .total(contractRequest.total())
                .type(contractRequest.type())
                .rating(contractRequest.rating())
                .manager(contractRequest.manager())
                .initiationContract(contractRequest.initiationContract())
                .endContract(contractRequest.endContract())
                .modality(contractRequest.modality())
                .build();
    }

    public static ContractResponse toContractResponse(Contract contract){
        return ContractResponse.builder()
                .id(contract.getId())
                .name(contract.getName())
                .number(contract.getNumber())
                .balance(contract.getBalance())
                .total(contract.getTotal())
                .type(contract.getType())
                .rating(contract.getRating())
                .manager(contract.getManager())
                .pdfPathFile(contract.getPdfPathFile())
                .initiationContract(contract.getInitiationContract())
                .endContract(contract.getEndContract())
                .modality(contract.getModality())
                .build();
    }
}
