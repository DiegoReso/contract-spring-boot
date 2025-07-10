package dev.reso.workshop.contract.controller.request;

import dev.reso.workshop.contract.entities.Manager;
import dev.reso.workshop.contract.enums.ContractType;
import dev.reso.workshop.contract.enums.Modality;
import java.util.Date;


public record ContractRequest(String name,
                              int number,
                              double balance,
                              double total,
                              ContractType type,
                              String rating,
                              Manager manager,
                              Date initiationContract,
                              Date endContract,
                              Modality modality
                              ) {
}
