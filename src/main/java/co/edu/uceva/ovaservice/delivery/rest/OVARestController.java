package co.edu.uceva.ovaservice.delivery.rest;

import co.edu.uceva.ovaservice.domain.exception.NoHayovasException;
import co.edu.uceva.ovaservice.domain.model.OVA;
import co.edu.uceva.ovaservice.domain.service.IOVAService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/OVA-service")

public class OVARestController {
    private final IOVAService OVAService;

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String OVA = "ova";
    private static final String OVAS = "ovas";

    @Autowired
    public OVARestController(IOVAService ovaService) {this.OVAService = ovaService; }


    /**
     * Listar todos los ovas.
     */
    @GetMapping("/ovas")
    public ResponseEntity<Map<String, Object>> getOVAS() {
        List<OVA> ovas = OVAService.findAll();
        if (ovas.isEmpty()) {
            throw new NoHayovasException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(OVAS, ovas);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar productos con paginación.
     */
    @GetMapping("/ovas/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<OVA> ovas = OVAService.findAll(pageable);

            if (ovas.isEmpty()) {
                response.put(MENSAJE, "No hay ovas en la página solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(ovas);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (IllegalArgumentException e) {
            response.put(MENSAJE, "Número de página inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Crear un nuevo ova pasando el objeto en el cuerpo de la petición.
     */
    @PostMapping("/ovas")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody OVA ova , BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }


        try {
            // Guardar el ova en la base de datos
            OVA nuevoOVA = OVAService.save(ova);

            response.put(MENSAJE, "El ova ha sido creado con éxito!");
            response.put(OVA, nuevoOVA);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar el ova en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    /**
     * Eliminar un ova pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/ovas")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody OVA ova) {
        Map<String, Object> response = new HashMap<>();
        try {
            OVA ovaExistente = OVAService.findById(ova.getId());
            if (ovaExistente == null) {
                response.put(MENSAJE, "El ova ID: " + ova.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            OVAService.delete(ova);
            response.put(MENSAJE, "El ova ha sido eliminado con éxito!");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al eliminar el ova de la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Actualizar un ova pasando el objeto en el cuerpo de la petición.
     * @param ova: Objeto OVA que se va a actualizar
     */
    @PutMapping("/ovas")
    public ResponseEntity<Map<String, Object>> update(@RequestBody OVA ova , BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Verificar si el ova existe antes de actualizar
            if (OVAService.findById(ova.getId()) == null) {
                response.put(MENSAJE, "Error: No se pudo editar, el ova ID: " + ova.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Guardar directamente el ova actualizado en la base de datos
            OVA ovaActualizado = OVAService.save(ova);

            response.put(MENSAJE, "El ova ha sido actualizado con éxito!");
            response.put(OVA, ovaActualizado);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al actualizar el ova en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener un ova por su ID.
     */
    @GetMapping("/ovas/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            OVA ova = OVAService.findById(id);

            if (ova == null) {
                response.put(MENSAJE, "El ova ID: " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put(MENSAJE, "El ova ha sido actualizado con éxito!");
            response.put(OVA,ova);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}















































/*
    @GetMapping("/ovas")
    public List<OVA> getOVAS() {return OVAService.findAll();}

    @GetMapping("/productos/page/{page}")
    public Page<OVA> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return OVAService.findAll(pageable);
    }

    @PostMapping("/ovas")
    public OVA save(@RequestBody OVA ova) {return OVAService.save(ova);}

    @DeleteMapping("/ovas")
    public void delete(@RequestBody OVA ova) {OVAService.delete(ova);}

    @PutMapping("/ovas")
    public OVA update(@RequestBody OVA ova){
        return OVAService.update(ova);
    }

    @GetMapping("/ovas/{id}")
    public OVA findById(@PathVariable("id") Long id) { return OVAService.findById(id);}
}
*/