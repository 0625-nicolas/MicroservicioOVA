package co.edu.uceva.ovaservice.domain.exception;

public class OvaNoEncontradoException extends RuntimeException {
    public OvaNoEncontradoException(Long id) {
        super("El ova con ID " + id + " no fue encontrado.");
    }
}
