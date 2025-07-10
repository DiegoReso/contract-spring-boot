package dev.reso.workshop.contract.exceptions;

public class FileStorageException extends RuntimeException {
  public FileStorageException(String message) {
    super(message);
  }
}
