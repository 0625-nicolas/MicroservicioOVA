package co.edu.uceva.ovaservice.domain.exception;

public class OvaExistenteException extends RuntimeException {
    public OvaExistenteException(String nombre) {
        super("El ova con nombre '" + nombre + "' ya existe.");
    }
}
