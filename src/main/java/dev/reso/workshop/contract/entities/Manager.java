package dev.reso.workshop.contract.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "managers")
public class Manager {
    @Id
    private String id;
    private String name;
    private String role;
    private String division;
}
