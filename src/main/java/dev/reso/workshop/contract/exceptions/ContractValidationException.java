package dev.reso.workshop.contract.exceptions;

public class ContractValidationException extends RuntimeException {
  public ContractValidationException(String message) {
    super(message);
  }
}
