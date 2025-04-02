package co.edu.uceva.ovaservice.domain.exception;

public class NoHayovasException extends RuntimeException {
    public NoHayovasException() {
      super("No hay ovas en la base de datos");
    }

}

