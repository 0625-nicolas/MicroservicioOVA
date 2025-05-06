package co.edu.uceva.ovaservice.delivery.rest;

import co.edu.uceva.ovaservice.domain.exception.NoHayOvasException;
import co.edu.uceva.ovaservice.domain.exception.OvaNoEncontradoException;
import co.edu.uceva.ovaservice.domain.exception.PaginaSinOvasException;
import co.edu.uceva.ovaservice.domain.exception.ValidationException;
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
@RequestMapping("/api/v1/ova-service")

public class OVARestController {
    private final IOVAService OVAService;

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String OVA = "ova";
    private static final String OVAS = "ovas";

    @Autowired
    public OVARestController(IOVAService ovaService) {this.OVAService = ovaService; }


    /**
     * Listar todos las ovas.
     */
    @GetMapping("/ovas")
    public ResponseEntity<Map<String, Object>> getProductos() {
        List<OVA> productos = OVAService.findAll();
        if (productos.isEmpty()) {
            throw new NoHayOvasException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(OVAS, productos);
        return ResponseEntity.ok(response);
    }

    /**
     * Listar productos con paginación.
     */
    @GetMapping("/ovas/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<OVA> productos = OVAService.findAll(pageable);
        if (productos.isEmpty()) {
            throw new PaginaSinOvasException(page);
        }
        return ResponseEntity.ok(productos);
    }

    /**
     * Crear un nuevo ova pasando el objeto en el cuerpo de la petición.
     */
    @PostMapping("/ovas")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody OVA ova, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        OVA nuevoOVA = OVAService.save(ova);
        response.put(MENSAJE, "El ova ha sido creado con éxito!");
        response.put(OVA, nuevoOVA);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    /**
     * Eliminar un ova pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/ovas")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody OVA ova) {
        OVAService.findById(ova.getId())
                .orElseThrow(() -> new OvaNoEncontradoException(ova.getId()));
        OVAService.delete(ova);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El ova ha sido eliminado con éxito!");
        response.put(OVA, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un ova pasando el objeto en el cuerpo de la petición.
     * @param ova: Objeto OVA que se va a actualizar
     */
    @PutMapping("/ovas")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody OVA ova, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        OVAService.findById(ova.getId())
                .orElseThrow(() -> new OvaNoEncontradoException(ova.getId()));
        Map<String, Object> response = new HashMap<>();
        OVA OvaActualizado = OVAService.update(ova);
        response.put(MENSAJE, "El ova ha sido actualizado con éxito!");
        response.put(OVA, OvaActualizado);
        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un ova por su ID.
     */
    @GetMapping("/ovas/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        OVA ova = OVAService.findById(id)
                .orElseThrow(() -> new OvaNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El OVA ha sido encontrado con éxito!");
        response.put(OVA, ova);
        return ResponseEntity.ok(response);
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