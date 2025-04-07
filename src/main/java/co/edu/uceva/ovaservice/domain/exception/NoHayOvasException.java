
package co.edu.uceva.ovaservice.domain.exception;

public class NoHayOvasException extends RuntimeException {
    public NoHayOvasException() {
      super("No hay ovas en la base de datos");
    }

}

