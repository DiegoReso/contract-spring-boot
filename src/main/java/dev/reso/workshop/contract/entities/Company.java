package dev.reso.workshop.contract.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(value = "companies")
public class Company {

    @Id
    private String id;
    private String name;
    private String email;
    private String cpnj;
    private String phone;
    private String address;

}
