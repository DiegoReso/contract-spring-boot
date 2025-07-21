package dev.reso.workshop.contract.entities;

import dev.reso.workshop.contract.enums.ContractType;
import dev.reso.workshop.contract.enums.Modality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "contracts")
public class Contract {

    @Id
    private String id;
    private String name;
    private int number;
    private double balance;
    private double total;
    private ContractType type;
    private String rating;
    private Manager manager;
    private String pdfPathFile;
    private Date initiationContract;
    private Date endContract;
    private Modality modality;
    private Company company;
}
