package dev.reso.workshop.contract.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter

public class StandardError {


    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}