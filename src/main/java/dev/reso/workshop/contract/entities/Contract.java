package dev.reso.workshop.contract.entities;

import dev.reso.workshop.contract.enums.ContractType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    private String id;
    private String name;
    private double balance;
    private double total;
    private ContractType type;
}
