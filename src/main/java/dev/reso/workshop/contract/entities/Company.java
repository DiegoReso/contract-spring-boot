package dev.reso.workshop.contract.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company {

    private String name;
    private String email;
    private String cpnj;
    private String phone;
    private String address;

}
