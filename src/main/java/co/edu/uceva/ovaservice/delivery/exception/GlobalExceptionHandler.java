package co.edu.uceva.ovaservice.delivery.exception;

import co.edu.uceva.ovaservice.domain.exception.*;
import co.edu.uceva.ovaservice.domain.model.OVA;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String OVA = "producto";
    private static final String OVAS = "productos";
    private static final String STATUS = "status";

    @ExceptionHandler(PaginaSinProductosException.class)
    public ResponseEntity<Map<String, Object>> handlePaginaSinProductos(PaginaSinProductosException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
