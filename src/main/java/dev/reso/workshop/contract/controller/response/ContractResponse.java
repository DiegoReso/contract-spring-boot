package dev.reso.workshop.contract.controller.response;

import dev.reso.workshop.contract.entities.Manager;
import dev.reso.workshop.contract.enums.ContractType;
import dev.reso.workshop.contract.enums.Modality;
import lombok.Builder;

import java.util.Date;

@Builder
public record ContractResponse(String id,
                               String name,
                               int number,
                               double balance,
                               double total,
                               ContractType type,
                               String rating,
                               Manager manager,
                               String pdfPathFile,
                               Date initiationContract,
                               Date endContract,
                               Modality modality,
                               CompanyResponse company
                               ) {
}
