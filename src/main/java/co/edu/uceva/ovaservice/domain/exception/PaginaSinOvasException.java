package co.edu.uceva.ovaservice.domain.exception;

public class PaginaSinOvasException extends RuntimeException {
    public PaginaSinOvasException(int page) {
        super("No hay ovas en la página solicitada: " + page);
    }
}
